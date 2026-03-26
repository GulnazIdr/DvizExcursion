package org.example.project.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.request.basicAuth
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.example.project.feature.auth.domain.AuthConfig
import org.example.project.feature.auth.domain.model.TokenResponse
import org.example.project.feature.auth.domain.token.TokenDataRepository

class AuthViewModel(
    private val tokenDataRepository: TokenDataRepository,
    private val httpClient: HttpClient
) : ViewModel() {
    fun exchangeCodeForToken(code: String) {
        viewModelScope.launch {
            try {
                val response = httpClient.post(AuthConfig.TOKEN_URI) {
                    contentType(ContentType.Application.FormUrlEncoded)
                    setBody(
                        FormDataContent(
                            Parameters.build {
                                append("grant_type", "authorization_code")
                                append("code", code)
                                append("redirect_uri", AuthConfig.CALLBACK_URL)
                            }
                        )
                    )
                    basicAuth(username = AuthConfig.CLIENT_ID, password = AuthConfig.CLIENT_SECRET)
                }

                val rawBody = response.bodyAsText()

                val json = Json { ignoreUnknownKeys = true }
                val tokenResponse = json.decodeFromString<TokenResponse>(rawBody)
                tokenDataRepository.saveAccessToken(tokenResponse.access_token)
                tokenDataRepository.saveRefreshToken(tokenResponse.refresh_token)
            } catch (e: Exception) {
                Napier.e("exchange error: ${e.message}")
            }
        }
    }
}