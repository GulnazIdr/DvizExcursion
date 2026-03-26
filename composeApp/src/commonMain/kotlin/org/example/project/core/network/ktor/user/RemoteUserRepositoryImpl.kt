package org.example.project.core.network.ktor.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.project.core.model.Email
import org.example.project.core.model.User
import org.example.project.core.network.CustomServerException
import org.example.project.core.network.ktor.models.KtorDataWrapping
import org.example.project.feature.auth.domain.token.TokenRepository

class RemoteUserRepositoryImpl(
    private val client: HttpClient,
    private val tokenRepository: TokenRepository
) : RemoteUserRepository {
    override suspend fun getCurrentUser(): Result<KtorDataWrapping<User>> {
        return runCatching {
            client.get(urlString = "stepics/1")
        }.map { response ->
            when (response.status.value) {
                401 -> tokenRepository.refreshToken()
                in 500..511 -> throw CustomServerException("server error $response")
            }

            val profiles = response.body<StepikCurrentProfileDto>().profiles
            if (profiles.isEmpty()) throw CustomServerException("current user not found")

            KtorDataWrapping(
                data = profiles.first().toUser(),
                isFromCache = false
            )
        }.fold(
            onSuccess = { ktorWrapping ->
                Result.success(KtorDataWrapping(ktorWrapping.data, false))
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }

    override suspend fun getUserList(idList: List<Int>): Result<KtorDataWrapping<List<User>>> {
        return runCatching {
            client.get(urlString = "users"){
                idList.forEach { id ->
                    parameter("ids", id)
                }
            }
        }.map { response ->
            when (response.status.value) {
                401 -> tokenRepository.refreshToken()
                in 500..511 -> throw CustomServerException("server error $response")
            }
            KtorDataWrapping(
                data = response.body<StepikAuthorDto>().users.map { it.toUser() },
                isFromCache = false
            )
        }.fold(
            onSuccess = { ktorWrapping ->
                if (ktorWrapping.data.isEmpty()) {
                    throw CustomServerException("users don't exist")
                }
                Result.success(KtorDataWrapping(ktorWrapping.data, false))
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }

    private suspend fun getEmailAddressesById(id: Int): Result<List<Email>> {
        return runCatching {
            client.get(urlString = "email-addresses/$id")
        }.map { response ->
            response.body<StepikEmailDto>().toEmailList()
        }
    }

    private fun StepikEmailDto.toEmailList(): List<Email> {
        return emailAddress.map { it.toEmail() }
    }

    private fun EmailDto.toEmail(): Email {
        return Email(
            id = id,
            email = email
        )
    }

    private suspend fun ProfileDto.toUser(): User{
        var email = ""
        val emailId = if (emailAddresses.isEmpty()) -1 else emailAddresses.first()
        if (emailId != -1){
            getEmailAddressesById(emailId).onSuccess { emailList ->
                if (emailList.isNotEmpty()) {
                    email = emailList.first().email
                }
            }
        }

        return User(
            id = id,
            name = fullName,
            details = details,
            shortBio = shortBio,
            profileImg = avatar,
            email = email
        )
    }

    private fun AuthorDto.toUser(): User{
        return User(
            id = id,
            name = fullName,
            profileImg = avatar
        )
    }
}

