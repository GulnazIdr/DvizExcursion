package org.example.project.feature.auth.domain

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
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
import kotlinx.serialization.json.Json
import net.openid.appauth.AuthorizationService
import net.openid.appauth.GrantTypeValues
import net.openid.appauth.TokenRequest
import org.example.project.feature.auth.domain.AppAuth.serviceConfiguration
import org.example.project.feature.auth.domain.model.TokenResponse
import org.example.project.feature.auth.domain.token.TokenStorage

class AppAuthHandler(
    private val activity: ComponentActivity,
    private val authService: AuthorizationService
) {
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val authRepository = AuthRepository()

    fun init() {
        launcher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}
    }

    fun open() {
        val authRequest = authRepository.getAuthRequest()

        val openAuthPageIntent = authService.getAuthorizationRequestIntent(
            authRequest
        )

        launcher.launch(openAuthPageIntent)
    }

    suspend fun exchangeCodeForToken(code: String, httpClient: HttpClient): TokenResponse? {
        return try {
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

            val json = Json {ignoreUnknownKeys = true
            }
            val tokenResponse = json.decodeFromString<TokenResponse>(rawBody)
            tokenResponse
        } catch (e: Exception) {
            Napier.e("exchange error: ${e.message}")
            null
        }
    }

    suspend fun handlePastedUrl(
        urlString: String,
        httpClient: HttpClient
    ): Result<Pair<String?, String>> {
        val uri = urlString.toUri()
        val code = uri.getQueryParameter("code")

        if (!code.isNullOrEmpty()) {
            val tokenResponse = exchangeCodeForToken(code, httpClient)

            if (tokenResponse != null) {
                TokenStorage.accessToken = tokenResponse.access_token
                TokenStorage.refreshToken = tokenResponse.refresh_token
                TokenStorage.idToken = tokenResponse.id_token
                TokenStorage.expiresIn = tokenResponse.expires_in

                return Result.success(
                    Pair(
                        tokenResponse.refresh_token,
                        tokenResponse.access_token
                    )
                )
            }
        }
        return Result.failure(Exception("Failed"))
    }

    fun getRefreshTokenRequest(refreshToken: String): TokenRequest {
        return TokenRequest.Builder(
            serviceConfiguration,
            AuthConfig.CLIENT_ID
        )
            .setGrantType(GrantTypeValues.REFRESH_TOKEN)
            .setRefreshToken(refreshToken)
            .build()
    }

}