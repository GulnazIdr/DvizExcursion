package org.gulnazidr.stepik.core.network.ktor.user.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.gulnazidr.stepik.core.domain.cancellationRunCatching
import org.gulnazidr.stepik.core.model.Email
import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.core.network.CustomServerException
import org.gulnazidr.stepik.core.network.RequestTimeOutException
import org.gulnazidr.stepik.core.network.TokenRefreshException
import org.gulnazidr.stepik.core.network.ktor.user.models.AuthorDto
import org.gulnazidr.stepik.core.network.ktor.user.models.EmailDto
import org.gulnazidr.stepik.core.network.ktor.user.models.ProfileDto
import org.gulnazidr.stepik.core.network.ktor.user.models.StepikAuthorDto
import org.gulnazidr.stepik.core.network.ktor.user.models.StepikCurrentProfileDto
import org.gulnazidr.stepik.core.network.ktor.user.models.StepikEmailDto
import org.gulnazidr.stepik.core.network.ktor.user.source.KtorUserRepository
import org.gulnazidr.stepik.core.domain.auth.TokenRepository

class KtorUserRepositoryImpl(
    private val client: HttpClient,
    private val tokenRepository: TokenRepository
) : KtorUserRepository {
    override suspend fun getCurrentUser(): Result<User> {
        return cancellationRunCatching {
            client.get(urlString = "stepics/1")
        }.map { response ->
            when (response.status.value) {
                401 -> {
                    val isRefreshed = tokenRepository.refreshToken()
                    if (!isRefreshed) {
                        throw TokenRefreshException("failed to refresh token")
                    } else {
                        getCurrentUser()
                    }
                }

                408 -> throw RequestTimeOutException("waiting time exceeded")

                in 500..511 -> throw CustomServerException("server error $response")
            }
            val profiles = response.body<StepikCurrentProfileDto>().profiles
            if (profiles.isEmpty()) throw CustomServerException("current user not found")
            profiles.first().toUser()
        }
    }

    override suspend fun getUserList(idList: List<Int>): Result<List<User>> {
        return cancellationRunCatching {
            client.get(urlString = "users") {
                idList.forEach { id ->
                    parameter("ids", id)
                }
            }
        }.map { response ->
            when (response.status.value) {
                401 -> {
                    val isRefreshed = tokenRepository.refreshToken()
                    if (!isRefreshed) {
                        throw TokenRefreshException("failed to refresh token")
                    } else {
                        getCurrentUser()
                    }
                }

                408 -> throw RequestTimeOutException("waiting time exceeded")

                in 500..511 -> throw CustomServerException("server error $response")
            }
            val users = response.body<StepikAuthorDto>().users
            if (users.isEmpty()) throw CustomServerException("current user not found")
            users.map { it.toUser() }
        }
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

    private suspend fun ProfileDto.toUser(): User {
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

    private fun AuthorDto.toUser(): User {
        return User(
            id = id,
            name = fullName,
            profileImg = avatar
        )
    }
}