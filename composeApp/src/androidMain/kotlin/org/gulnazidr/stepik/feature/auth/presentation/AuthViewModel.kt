package org.gulnazidr.stepik.feature.auth.presentation

import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.gulnazidr.stepik.core.domain.auth.TokenDataRepository
import org.gulnazidr.stepik.core.domain.user.UserRepository
import org.gulnazidr.stepik.feature.auth.domain.models.AuthConfig
import org.gulnazidr.stepik.feature.auth.domain.models.TokenResponse

class AuthViewModel(
    private val tokenDataRepository: TokenDataRepository,
    private val httpClient: HttpClient,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _tokenExchangeState = MutableSharedFlow<Boolean>()
    val tokenExchangeState: SharedFlow<Boolean> = _tokenExchangeState.asSharedFlow()
  //  private val _isLoading = mutableStateOf(t)
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

                val res = tokenDataRepository.saveAccessToken(tokenResponse.access_token) &&
                            tokenDataRepository.saveRefreshToken(tokenResponse.refresh_token)
                if (res){
                    val res = userRepository.getCurrentUser()
                    if (res.isSuccess) {
                        Napier.wtf("user1 $res")
                        _tokenExchangeState.emit(true)
                    }
                }
            } catch (e: Exception) {
                Napier.e("exchange error: ${e.message}")
            }
        }
    }
}