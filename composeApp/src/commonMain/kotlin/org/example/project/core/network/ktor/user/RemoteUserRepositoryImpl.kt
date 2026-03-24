package org.example.project.core.network.ktor.user

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.utils.EmptyContent.headers
import org.example.project.core.model.Email
import org.example.project.core.model.StepikEmail
import org.example.project.core.model.User
import org.example.project.core.network.ktor.CustomServerException
import org.example.project.core.network.ktor.HttpRoutes
import org.example.project.core.network.ktor.models.KtorDataWrapping
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.auth.domain.token.TokenRepository
import org.example.project.feature.auth.domain.token.TokenStorage

class RemoteUserRepositoryImpl(
    private val client: HttpClient,
    private val tokenRepository: TokenRepository,
    private val tokenDataRepository: TokenDataRepository
) : RemoteUserRepository {
    override suspend fun getCurrentUser(): Result<KtorDataWrapping<User>> {
        return runCatching {
            client.get(urlString = HttpRoutes.CURRENT_USER_PROFILE){
                header("Authorization", "Bearer ${tokenDataRepository.getAccessToken()}")
            }
        }.map { response ->
            when (response.status.value) {
                401 -> tokenRepository.refreshToken()
                in 500..511 -> throw CustomServerException("server error $response")
            }
            KtorDataWrapping(
                data = response.body<StepikUserDto>().toUser(),
                isFromCache = false
            )
        }.fold(
            onSuccess = { ktorWrapping ->
                if (ktorWrapping.data == null) {
                    throw NullPointerException("user doesnt exist")
                }
                Result.success(KtorDataWrapping(ktorWrapping.data, false))
            },
            onFailure = { error ->
                Result.failure(error)
            }
        )
    }

    override suspend fun getUserById(id: Int): Result<KtorDataWrapping<StepikEmail>> {
        TODO("Not yet implemented")
    }

    private suspend fun getEmailAddressesById(id: Int): Result<List<Email>> {
        return runCatching {
            client.get(urlString = HttpRoutes.EMAILS + "/$id") {
                header("Authorization", "Bearer ${tokenDataRepository.getAccessToken()}")
            }
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

    private suspend fun StepikUserDto.toUser(): User? {
        val profile = if (profiles.isNotEmpty()) profiles.first() else null
        var email = ""

        return if (profile == null) {
            null
        } else {
            val emailId = if (profile.emailAddresses.isEmpty()) -1 else profile.emailAddresses.first()
            if (emailId != -1){
                getEmailAddressesById(emailId).onSuccess { emailList ->
                    if (emailList.isNotEmpty()) {
                        email = emailList.first().email
                    }
                }
            }

            User(
                id = profile.id,
                name = profile.fullName,
                details = profile.details,
                shortBio = profile.shortBio,
                profileImg = profile.avatar,
                email = email
            )
        }
    }
}

