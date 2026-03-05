package org.example.project.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.login_text
import dvizexcursion.composeapp.generated.resources.password_hint
import dvizexcursion.composeapp.generated.resources.remember_me_text
import dvizexcursion.composeapp.generated.resources.user_name_hint
import org.example.project.presentation.components.InputField
import org.example.project.presentation.components.NavigationButton
import org.example.project.presentation.components.TextCheckBox
import org.example.project.presentation.auth.components.AnimatedBorderCard
import org.example.project.presentation.auth.components.AuthTopAppBar
import org.example.project.presentation.auth.components.RedditLogo
import org.example.project.presentation.auth.models.AuthUiEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    navigateToMain: () -> Unit,
    navigateToRegistration: () -> Unit,
    onBack: () -> Unit
) {
    val loginViewModel = koinViewModel<LoginViewModel>()
    val loginUiState = loginViewModel.loginUiState.collectAsState().value
    val loginUiEvent = loginViewModel.authUiEvent

    LaunchedEffect(loginUiEvent){
        loginUiEvent.collect { event ->
            when(event){
                is AuthUiEvent.AuthSuccessEvent -> {navigateToMain()}
            }
        }
    }
    Scaffold{ paddingValues ->
        AnimatedBorderCard(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AuthTopAppBar(
                    onBack = onBack,
                    modifier = Modifier.padding(paddingValues),
                    isRegistationScreen = false,
                    onAuth = navigateToRegistration
                )
                Spacer(modifier = Modifier.weight(1f))

                RedditLogo(
                    size = 60.dp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(Res.string.login_text),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))

                InputField(
                    value = loginUiState.userName,
                    onValueChange = { loginViewModel.onUserNameChanged(it) },
                    hint = stringResource(Res.string.user_name_hint)
                )

                Spacer(modifier = Modifier.height(20.dp))

                InputField(
                    value = loginUiState.password,
                    onValueChange = { loginViewModel.onPasswordChanged(it) },
                    hint = stringResource(Res.string.password_hint),
                    isPasswordField = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextCheckBox(
                    text = stringResource(Res.string.remember_me_text)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = loginUiState.error.asString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(30.dp))

                NavigationButton(
                    onBtnClick = { loginViewModel.login() },
                    text = stringResource(Res.string.login_text),
                    isEnabled = loginUiState.isLoginButtonActive
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPrev() {
    LoginScreen(
        onBack = {},
        navigateToMain = {},
        navigateToRegistration = {}
    )
}