package org.example.project.feature.auth.presentation.register

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.email_hint
import dvizexcursion.composeapp.generated.resources.password_hint
import dvizexcursion.composeapp.generated.resources.policy_text
import dvizexcursion.composeapp.generated.resources.registration_text
import dvizexcursion.composeapp.generated.resources.user_name_hint
import org.example.project.core.designsystem.components.InputField
import org.example.project.core.designsystem.components.NavigationButton
import org.example.project.core.designsystem.components.StepikLogo
import org.example.project.core.designsystem.components.TextCheckBox
import org.example.project.feature.auth.presentation.components.AnimatedBorderCard
import org.example.project.feature.auth.presentation.components.AuthTopAppBar
import org.example.project.feature.auth.presentation.models.AuthUiEvent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RegistrationScreen(
    navigateToMain: () -> Unit,
    navigateToLogin: () -> Unit,
    registrViewModel: RegistrationViewmodel = koinViewModel<RegistrationViewmodel>(),
    onBack: () -> Unit
){
    val registrUiState = registrViewModel.registerUiState.collectAsStateWithLifecycle().value
    val loginUiEvent = registrViewModel.authUiEvent

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
                    isRegistationScreen = true,
                    modifier = Modifier.padding(paddingValues),
                    onAuth = navigateToLogin
                )
                Spacer(modifier = Modifier.weight(1f))

                StepikLogo(
                    size = 60.dp
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = stringResource(Res.string.registration_text),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                )

                Spacer(modifier = Modifier.height(40.dp))

                InputField(
                    value = registrUiState.userName,
                    onValueChange = { registrViewModel.onUserNameChanged(it) },
                    hint = stringResource(Res.string.user_name_hint),
                    errorText = registrUiState.nameError?.asString() ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                InputField(
                    value = registrUiState.password,
                    onValueChange = { registrViewModel.onPasswordChanged(it) },
                    hint = stringResource(Res.string.password_hint),
                    isPasswordField = true,
                    errorText = registrUiState.pswdError?.asString() ?: ""
                )

                Spacer(modifier = Modifier.height(20.dp))

                InputField(
                    value = registrUiState.email,
                    onValueChange = { registrViewModel.onEmailChanged(it) },
                    hint = stringResource(Res.string.email_hint),
                    errorText = registrUiState.emailError?.asString() ?: ""
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextCheckBox(
                    text = stringResource(Res.string.policy_text)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = registrUiState.registerError?.asString() ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(30.dp))

                NavigationButton(
                    onBtnClick = { registrViewModel.register() },
                    text = stringResource(Res.string.registration_text),
                    isEnabled = registrUiState.isLoginButtonActive
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
fun RegistrationScreenPre(){
    RegistrationScreen(
        navigateToMain = {},
        navigateToLogin = {},
        onBack = {}
    )
}