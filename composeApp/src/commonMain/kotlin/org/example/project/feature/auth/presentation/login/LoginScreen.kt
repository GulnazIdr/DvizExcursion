package org.example.project.feature.auth.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.example.project.core.designsystem.components.InputField
import org.example.project.core.designsystem.components.NavigationButton
import org.example.project.core.designsystem.components.StepikLogo
import org.example.project.core.designsystem.components.TextCheckBox
import org.example.project.feature.auth.presentation.components.AuthTopAppBar
import org.example.project.feature.auth.presentation.components.StepikSignUp
import org.example.project.feature.auth.presentation.models.AuthUiEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.auth_password_hint
import stepik.composeapp.generated.resources.auth_user_name_hint
import stepik.composeapp.generated.resources.login_stepik_login_text
import stepik.composeapp.generated.resources.login_text
import stepik.composeapp.generated.resources.registration_remember_me_text

@Composable
fun LoginScreen(
    navigateToMain: () -> Unit,
    navigateToRegistration: () -> Unit,
    loginViewModel: LoginViewModel = koinViewModel<LoginViewModel>(),
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val loginUiState by loginViewModel.loginUiState.collectAsStateWithLifecycle()
    val loginUiEvent = loginViewModel.authUiEvent

    LaunchedEffect(loginUiEvent) {
        loginUiEvent.collect { event ->
            when (event) {
                is AuthUiEvent.AuthSuccessEvent -> {
                    navigateToMain()
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthTopAppBar(
            onBack = onBack,
            isRegistationScreen = false,
            onAuth = navigateToRegistration
        )
        Spacer(modifier = Modifier.weight(1f))

        StepikLogo(
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
            hint = stringResource(Res.string.auth_user_name_hint)
        )

        Spacer(modifier = Modifier.height(20.dp))

        InputField(
            value = loginUiState.password,
            onValueChange = { loginViewModel.onPasswordChanged(it) },
            hint = stringResource(Res.string.auth_password_hint),
            isPasswordField = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextCheckBox(
            text = stringResource(Res.string.registration_remember_me_text),
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

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = stringResource(Res.string.login_stepik_login_text),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )

        Spacer(modifier = Modifier.height(25.dp))

        StepikSignUp(
            navigateToMain = navigateToMain
        )

        Spacer(modifier = Modifier.weight(1f))
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