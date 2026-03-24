package org.example.project.feature.auth.presentation

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
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.ClientSecretPost
import net.openid.appauth.GrantTypeValues
import net.openid.appauth.TokenRequest
import org.example.project.domain.AuthConfigAndroid
import org.example.project.feature.auth.domain.AppAuth
import org.example.project.feature.auth.domain.AuthConfig
import org.example.project.feature.auth.domain.PlatformAuthService
import org.example.project.feature.auth.domain.model.TokenResponse
import org.example.project.feature.auth.domain.token.TokenStorage
import org.example.project.feature.auth.domain.token.TokensModel
import kotlin.coroutines.suspendCoroutine

class AppAuthHandler(
    private val activity: ComponentActivity,
    private val authService: AuthorizationService
) {
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val platformService: PlatformAuthService = PlatformAuthService()

    fun init() {
        launcher = activity.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {}
    }

    fun open() {
        val redirectUri = AuthConfig.CALLBACK_URL.toUri()
        val authRequest = AuthorizationRequest.Builder(
            AppAuth.authServiceConfig,
            AuthConfig.CLIENT_ID,
            AuthConfigAndroid.RESPONSE_TYPE,
            redirectUri
        )
            .build()

        val openAuthPageIntent = authService.getAuthorizationRequestIntent(
            authRequest
        )

        launcher.launch(openAuthPageIntent)
    }

    suspend fun handlePastedUrl(
        urlString: String,
        httpClient: HttpClient
    ): Result<Pair<String?, String>> {
        val uri = urlString.toUri()
        val code = uri.getQueryParameter("code")

        if (!code.isNullOrEmpty()) {
            val tokenResponse = exchangeCodeForToken(code, httpClient)

            Napier.wtf("id ${tokenResponse?.id_token}")

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

    suspend fun performRefreshToken(refreshToken: String){
        performTokenRefreshRequest(authService, refreshToken)
    }

    private suspend fun exchangeCodeForToken(
        code: String,
        httpClient: HttpClient
    ): TokenResponse? {
        return try {
            val response = httpClient.post(AuthConfig.TOKEN_URI) {
                contentType(ContentType.Application.FormUrlEncoded)
                setBody(
                    FormDataContent(
                        Parameters.Companion.build {
                            append("grant_type", "authorization_code")
                            append("code", code)
                            append("redirect_uri", AuthConfig.CALLBACK_URL)
                        }
                    )
                )
                basicAuth(username = AuthConfig.CLIENT_ID, password = AuthConfig.CLIENT_SECRET)
            }

            val rawBody = response.bodyAsText()

            val json = Json {
                ignoreUnknownKeys = true
            }
            val tokenResponse = json.decodeFromString<TokenResponse>(rawBody)
            tokenResponse
        } catch (e: Exception) {
            Napier.e("exchange error: ${e.message}")
            null
        }
    }

    private suspend fun performTokenRefreshRequest(
        authService: AuthorizationService,
        refreshToken: String
    ) {
        val request = TokenRequest.Builder(
            AppAuth.authServiceConfig,
            AuthConfig.CLIENT_ID
        )
            .setGrantType(GrantTypeValues.REFRESH_TOKEN)
            .setRefreshToken(refreshToken)
            .build()

        val tokens = performTokenRequestSuspend(authService, request)
        TokenStorage.accessToken = tokens.accessToken
        TokenStorage.refreshToken = tokens.refreshToken
        TokenStorage.idToken = tokens.idToken
    }

    private suspend fun performTokenRequestSuspend(
        authService: AuthorizationService,
        tokenRequest: TokenRequest,
    ): TokensModel {
        return suspendCoroutine { continuation ->
            authService.performTokenRequest(
                tokenRequest,
                ClientSecretPost(AuthConfig.CLIENT_SECRET)
            ) { response, ex ->
                when {
                    response != null -> {
                        platformService.settoken(response.accessToken.orEmpty())
                        val tokens = TokensModel(
                            accessToken = response.accessToken.orEmpty(),
                            refreshToken = response.refreshToken.orEmpty(),
                            idToken = response.idToken.orEmpty()
                        )

                        continuation.resumeWith(Result.success(tokens))
                    }

                    ex != null -> {
                        continuation.resumeWith(Result.failure(ex))
                    }

                    else -> error("unreachable")
                }
            }
        }
    }
}