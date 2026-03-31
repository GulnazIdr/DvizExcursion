package org.gulnazidr.stepik.feature.auth.presentation.components

import android.graphics.Bitmap
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import io.github.aakira.napier.Napier
import org.gulnazidr.stepik.core.designsystem.components.CircleLoading
import org.gulnazidr.stepik.feature.auth.domain.models.AuthConfig
import org.gulnazidr.stepik.feature.auth.presentation.AuthViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
actual fun StepikSignUp(
    signup: () -> Unit
) {
    val authViewmodel = koinViewModel<AuthViewModel>()

    Column {
        ButtonStepikLogin(
            onClick = { signup() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
actual fun AuthPage(
    onTokenRecieved: (String) -> Unit,
    navigateToMain: () -> Unit
) {
    val authViewmodel = koinViewModel<AuthViewModel>()
    val tokenExchangeState = authViewmodel.tokenExchangeState
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(tokenExchangeState) {
        tokenExchangeState.collect { isExchanged ->
            if (isExchanged) {
                navigateToMain()
            }
        }
    }

    Box{
        AndroidView(
            modifier = Modifier,
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true


                    WebView.setWebContentsDebuggingEnabled(true)

                    webViewClient = object : WebViewClient() {
                        override fun onPageFinished(
                            view: WebView?,
                            url: String?
                        ) {
                            super.onPageFinished(view, url)
                            isLoading = false
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?
                        ): Boolean {
                            val url = request?.url.toString()

                            if (url.startsWith(AuthConfig.CALLBACK_URL)) {
                                val token = url.toUri().getQueryParameter("code")
                                if (token != null) {
                                    authViewmodel.exchangeCodeForToken(token)
                                    return true
                                }
                                return false
                            }
                            return false
                        }
                    }


                    val authUrl =
                        "${AuthConfig.AUTH_URI}?response_type=code&client_id=${AuthConfig.CLIENT_ID}&redirect_uri=${AuthConfig.CALLBACK_URL}"

                    loadUrl(authUrl)
                }
            }
        )

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircleLoading()
            }
        }
    }

}