package org.example.project.feature.auth.domain

import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Parameters
import io.ktor.http.isSuccess
import net.openid.appauth.TokenResponse
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.auth.domain.token.TokenRepository
import org.example.project.feature.auth.domain.token.TokenStorage

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
                        Parameters.build {
                            append("grant_type", "refresh_token")
                            append("refresh_token", refreshToken)
                            append("client_id", AuthConfig.CLIENT_ID)
                            append("client_secret", AuthConfig.CLIENT_SECRET)
                        }
                    )
                    // header("Content-Type", "application/x-www-form-urlencoded")
                }

                if (response.status.isSuccess()) {
                    val response = response.body<TokenResponse>()
                    if (response.accessToken != null) {
                        val isRefreshSaved = tokenDataRepository.saveRefreshToken(
                            response.refreshToken
                        )
                        val isAccessSaved = tokenDataRepository.saveAccessToken(
                            response.accessToken!!
                        )

                        Napier.d("data tokens ${response.accessToken} ${response.refreshToken}")

                        isRefreshSaved && isAccessSaved
                    } else {
                        false
                    }
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

    override fun logout() {
        TokenStorage.accessToken = null
        TokenStorage.refreshToken = null
        TokenStorage.idToken = null
        tokenDataRepository.deleteAll()
    }
}