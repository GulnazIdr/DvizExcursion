package org.example.project.feature.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.login.LoginRepository
import org.example.project.feature.auth.domain.login.LoginUseCase
import org.example.project.feature.auth.presentation.models.AuthUiEvent
import org.example.project.feature.auth.presentation.models.LoginUiState
import org.example.project.core.designsystem.ui_logic.UiText
import org.example.project.feature.auth.domain.RemoteError
import org.example.project.feature.auth.presentation.mappers.asUiText
import org.example.project.feature.onboarding.domain.DataStoreRepository

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private val _loginUiState = MutableStateFlow(
        LoginUiState(
            userName = "",
            password = "", isLoginButtonActive = false,
            error = UiText.DynamicString("")
        )
    )
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private val _authUiEvent = MutableSharedFlow<AuthUiEvent>()
    val authUiEvent: SharedFlow<AuthUiEvent> = _authUiEvent.asSharedFlow()

    fun onUserNameChanged(userName: String){
        _loginUiState.update { state ->
            _loginUiState.value.copy(
                userName = userName,
                isLoginButtonActive = userName.isNotEmpty() && state.password.isNotEmpty(),
                error =
                    loginUseCase(userName, state.password)?.asUiText() ?:
                    UiText.DynamicString("")
            )
        }
    }

    fun onPasswordChanged(password: String){
        _loginUiState.update { state ->
            _loginUiState.value.copy(
                password = password,
                isLoginButtonActive = password.isNotEmpty() && state.userName.isNotEmpty(),
                error =
                    loginUseCase(state.userName, password)?.asUiText() ?:
                    UiText.DynamicString("")
            )
        }
    }

    fun login() {
        val loginState = _loginUiState.value
        viewModelScope.launch {
            if (loginState.password.isNotEmpty() && loginState.userName.isNotEmpty()) {
                when (val res = loginRepository.login(loginState.userName, loginState.password)) {

                    is AuthResult.Success<Result<Boolean>, RemoteError> -> {
                        _authUiEvent.emit(AuthUiEvent.AuthSuccessEvent)
                    }

                    is AuthResult.Error<Result<Boolean>, RemoteError> -> {
                        _loginUiState.update {
                            _loginUiState.value.copy(
                                error = res.error.asUiText()
                            )
                        }
                    }
                }
            }
        }
    }
}