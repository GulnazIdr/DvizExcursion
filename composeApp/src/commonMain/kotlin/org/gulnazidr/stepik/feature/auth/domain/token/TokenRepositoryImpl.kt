package org.gulnazidr.stepik.feature.auth.domain.token

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Parameters
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import org.gulnazidr.stepik.core.domain.auth.TokenDataRepository
import org.gulnazidr.stepik.core.domain.auth.TokenRepository
import org.gulnazidr.stepik.feature.auth.domain.models.AuthConfig
import org.gulnazidr.stepik.feature.auth.domain.models.TokenResponse

class TokenRepositoryImpl(
    private val tokenDataRepository: TokenDataRepository,
    private val httpClient: HttpClient
) : TokenRepository {

    override suspend fun refreshToken(): Boolean {
        val refreshToken = tokenDataRepository.getRefreshToken()
        return if (refreshToken != null) {
            try {
                val response = httpClient.post(AuthConfig.TOKEN_URI) {
                    setBody(
                        FormDataContent(
                            Parameters.Companion.build {
                                append("grant_type", "refresh_token")
                                append("refresh_token", refreshToken)
                                append("client_id", AuthConfig.CLIENT_ID)
                                append("client_secret", AuthConfig.CLIENT_SECRET)
                            }
                        )
                    )
                    header("Content-Type", "application/x-www-form-urlencoded")
                }

                if (response.status.isSuccess()) {
                    val rawBody = response.bodyAsText()

                    val json = Json { ignoreUnknownKeys = true }
                    val tokenResponse = json.decodeFromString<TokenResponse>(rawBody)
                    val isRefreshSaved = tokenDataRepository.saveRefreshToken(
                        tokenResponse.refresh_token
                    )
                    val isAccessSaved = tokenDataRepository.saveAccessToken(
                        tokenResponse.access_token
                    )
                    isRefreshSaved && isAccessSaved
                } else {
                    Napier.e("Refresh failed: ${response.status}")
                    false
                }
            } catch (e: Exception) {
                Napier.e("Refresh error: ${e.message}")
                false
            }
        }else{
            false
        }
    }

    override fun logout(): Boolean {
        TokenStorage.accessToken = null
        TokenStorage.refreshToken = null
        TokenStorage.idToken = null
        return tokenDataRepository.deleteAll()
    }
}