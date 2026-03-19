package org.example.project.feature.auth.presentation.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import org.example.project.core.designsystem.components.NavigationButton
import org.example.project.feature.auth.presentation.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
actual fun StepikSignUp(
    signup: () -> Unit,
){
    val viewModel: AuthViewModel = koinViewModel()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data ?: return@rememberLauncherForActivityResult

        val exception = AuthorizationException.fromIntent(data)
        val tokenExchangeRequest = AuthorizationResponse.fromIntent(data)
            ?.createTokenExchangeRequest()
        when {
            exception != null -> viewModel.onAuthCodeFailed(exception)
            tokenExchangeRequest != null ->
                viewModel.onAuthCodeReceived(tokenExchangeRequest)
        }
    }

    var handledIntentId by remember { mutableStateOf<Int?>(null) }

    val currentIntent = viewModel.openAuthPageFlow.collectAsState(null).value

    DisposableEffect(currentIntent, handledIntentId) {
        if (currentIntent != null) {
            if (currentIntent.hashCode() != handledIntentId) {
                //   handledIntentId = currentIntent.hashCode()
                launcher.launch(currentIntent)
            }
        }

        onDispose { }
    }

    NavigationButton(
        onBtnClick = {viewModel.openLoginPage()},
        text = "login stepik"
    )
}