package org.example.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.login_text
import dvizexcursion.composeapp.generated.resources.password_hint
import dvizexcursion.composeapp.generated.resources.remember_me_text
import dvizexcursion.composeapp.generated.resources.user_email_hint
import io.github.aakira.napier.Napier
import org.example.project.components.AnimatedBorderCard
import org.example.project.components.BlurryCircle
import org.example.project.components.InputField
import org.example.project.components.NavigationButton
import org.example.project.components.TopAppBar
import org.example.project.theme.RedditTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen(
    navigateToMain: () -> Unit,
    onBack: () -> Unit,
) {
    val authViewModel: AuthViewModel = viewModel()
    val loginUiState = authViewModel.loginUiState.collectAsState().value
    val loginUiEvent = authViewModel.loginUiEvent

    var isRememberChecked by rememberSaveable { mutableStateOf(false) }

    val height = LocalWindowInfo.current.containerDpSize.height.value
    val width = LocalWindowInfo.current.containerDpSize.width.value

    LaunchedEffect(loginUiEvent){
        Napier.d("worked", tag = "some")
        loginUiEvent.collect { event ->
            if (event is LoginUiEvent.LoginSuccessEvent) navigateToMain()
        }
    }

    RedditTheme {
        AnimatedBorderCard(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
        ) {
            Box(
                modifier = Modifier.padding(top = (height * 0.02).dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .zIndex(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TopAppBar(
                        onBack = onBack
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(Res.string.login_text),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 28.sp,
                        lineHeight = 39.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    InputField(
                        value = loginUiState.userName,
                        onValueChange = { authViewModel.onUserNameChanged(it) },
                        hint = stringResource(Res.string.user_email_hint)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    InputField(
                        value = loginUiState.password,
                        onValueChange = { authViewModel.onPasswordChanged(it) },
                        hint = stringResource(Res.string.password_hint),
                        isPasswordField = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isRememberChecked,
                            onCheckedChange = { isRememberChecked = it },
                            colors = CheckboxDefaults.colors(
                                uncheckedBorderColor = MaterialTheme.colorScheme.primary,
                                checkedBorderColor = MaterialTheme.colorScheme.primary,
                                checkedBoxColor = Color.White,
                                checkedCheckmarkColor = MaterialTheme.colorScheme.primary,
                            )
                        )

                        Text(
                            text = stringResource(Res.string.remember_me_text),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = loginUiState.error,
                        color = Color.Red,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    NavigationButton(
                        onBtnClick = {authViewModel.login()},
                        text = stringResource(Res.string.login_text),
                      //  isEnabled = loginUiState.isLoginButtonActive
                    )

                    Spacer(modifier = Modifier.weight(1f))
                }

                BlurryCircle(
                    offsetX = (width * 0.1).dp,
                    offsetY = (height * 0.35).dp,
                    size = 200.dp,
                    color = MaterialTheme.colorScheme.outline,
                )

                BlurryCircle(
                    offsetX = (width * 0.3).dp,
                    offsetY = (height * 0.02).dp,
                    size = 250.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                BlurryCircle(
                    offsetX = (width * 0.4).dp,
                    offsetY = (height * 0.7).dp,
                    size = 180.dp,
                    color = MaterialTheme.colorScheme.secondaryContainer,
                )
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