//package org.example.project.feature.auth.presentation
//
//import android.content.Intent
//import androidx.activity.ComponentActivity
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import net.openid.appauth.AuthorizationService
//import net.openid.appauth.ClientSecretPost
//import net.openid.appauth.GrantTypeValues
//import net.openid.appauth.TokenRequest
//import org.example.project.feature.auth.domain.AppAuth
//import org.example.project.feature.auth.domain.AuthConfig
//import org.example.project.feature.auth.domain.PlatformAuthService
//import org.example.project.feature.auth.domain.token.TokenStorage
//import org.example.project.feature.auth.domain.token.TokensModel
//import kotlin.coroutines.suspendCoroutine
//
//class AppAuthHandler(
//    private val activity: ComponentActivity,
//    private val authService: AuthorizationService
//) {
//    private lateinit var launcher: ActivityResultLauncher<Intent>
//    private val platformService: PlatformAuthService = PlatformAuthService()
//
//    fun init() {
//        launcher = activity.registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()
//        ) {}
//    }
//
//    suspend fun performRefreshToken(refreshToken: String){
//        performTokenRefreshRequest(authService, refreshToken)
//    }
//
//    private suspend fun performTokenRefreshRequest(
//        authService: AuthorizationService,
//        refreshToken: String
//    ) {
//        val request = TokenRequest.Builder(
//            AppAuth.authServiceConfig,
//            AuthConfig.CLIENT_ID
//        )
//            .setGrantType(GrantTypeValues.REFRESH_TOKEN)
//            .setRefreshToken(refreshToken)
//            .build()
//
//        val tokens = performTokenRequestSuspend(authService, request)
//        TokenStorage.accessToken = tokens.accessToken
//        TokenStorage.refreshToken = tokens.refreshToken
//        TokenStorage.idToken = tokens.idToken
//    }
//
//    private suspend fun performTokenRequestSuspend(
//        authService: AuthorizationService,
//        tokenRequest: TokenRequest,
//    ): TokensModel {
//        return suspendCoroutine { continuation ->
//            authService.performTokenRequest(
//                tokenRequest,
//                ClientSecretPost(AuthConfig.CLIENT_SECRET)
//            ) { response, ex ->
//                when {
//                    response != null -> {
//                        platformService.settoken(response.accessToken.orEmpty())
//                        val tokens = TokensModel(
//                            accessToken = response.accessToken.orEmpty(),
//                            refreshToken = response.refreshToken.orEmpty(),
//                            idToken = response.idToken.orEmpty()
//                        )
//
//                        continuation.resumeWith(Result.success(tokens))
//                    }
//
//                    ex != null -> {
//                        continuation.resumeWith(Result.failure(ex))
//                    }
//
//                    else -> error("unreachable")
//                }
//            }
//        }
//    }
//}