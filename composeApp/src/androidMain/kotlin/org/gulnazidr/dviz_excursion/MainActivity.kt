package org.gulnazidr.dviz_excursion

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import io.github.aakira.napier.Napier
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import org.example.project.core.designsystem.theme.StepikTheme
import org.example.project.core.navigation.NavigationGraph
import org.example.project.feature.auth.domain.AuthConfig
import org.example.project.feature.auth.presentation.AuthViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            StepikTheme {
                NavigationGraph()
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        intent?.data?.let { uri ->
            if (uri.toString().startsWith(AuthConfig.CALLBACK_URL)) {
                handleAuthResponse(intent)
            }
        }
    }

    private fun handleAuthResponse(intent: Intent) {

        val response = AuthorizationResponse.fromIntent(intent)
        val error = AuthorizationException.fromIntent(intent)

        if (response != null) {
            exchangeToken(response)
        } else if (error != null) {
            Napier.e("response error $error")
        }
    }

    private fun exchangeToken(response: AuthorizationResponse) {
        val authService = AuthorizationService(this)
        val tokenRequest = response.createTokenExchangeRequest()

        authService.performTokenRequest(tokenRequest) { tokenResponse, tokenEx ->
            if (tokenEx != null) {
                Napier.e("token exception $tokenEx")
            } else if (tokenResponse != null) {
                Napier.d("token $tokenResponse")
               // viewModel.onTokenReceived(tokenResponse)
            }
            authService.dispose()
        }
    }
}