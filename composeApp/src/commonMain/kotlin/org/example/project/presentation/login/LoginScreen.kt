package org.example.project.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.login_text
import dvizexcursion.composeapp.generated.resources.password_hint
import dvizexcursion.composeapp.generated.resources.redidit_circle
import dvizexcursion.composeapp.generated.resources.remember_me_text
import dvizexcursion.composeapp.generated.resources.user_name_hint
import org.example.project.presentation.components.common.InputField
import org.example.project.presentation.components.common.NavigationButton
import org.example.project.presentation.components.common.TextCheckBox
import org.example.project.presentation.login.components.AnimatedBorderCard
import org.example.project.presentation.login.components.AuthTopAppBar
import org.example.project.presentation.login.components.RedditLogo
import org.example.project.presentation.login.models.LoginUiEvent
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = koinViewModel<AuthViewModel>(),
    navigateToMain: () -> Unit,
    onBack: () -> Unit,
) {
    val loginUiState = authViewModel.loginUiState.collectAsState().value
    val loginUiEvent = authViewModel.loginUiEvent

    LaunchedEffect(loginUiEvent){
        loginUiEvent.collect { event ->
            when(event){
                is LoginUiEvent.LoginSuccessEvent -> {navigateToMain()}
            }
        }
    }
    Scaffold{ paddingValues ->
        AnimatedBorderCard(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .zIndex(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AuthTopAppBar(
                    onBack = onBack,
                    modifier = Modifier.padding(paddingValues)
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
                    onValueChange = { authViewModel.onUserNameChanged(it) },
                    hint = stringResource(Res.string.user_name_hint)
                )

                Spacer(modifier = Modifier.height(20.dp))

                InputField(
                    value = loginUiState.password,
                    onValueChange = { authViewModel.onPasswordChanged(it) },
                    hint = stringResource(Res.string.password_hint),
                    isPasswordField = true
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextCheckBox(
                    text = stringResource(Res.string.remember_me_text)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = loginUiState.error,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(30.dp))

                NavigationButton(
                    onBtnClick = { authViewModel.login() },
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
        navigateToMain = {}
    )
}