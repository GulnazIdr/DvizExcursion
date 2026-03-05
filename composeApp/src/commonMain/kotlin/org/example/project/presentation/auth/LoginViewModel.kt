package org.example.project.presentation.auth

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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
import org.example.project.domain.auth.AuthResult
import org.example.project.domain.auth.login.LoginRepository
import org.example.project.domain.auth.login.LoginUseCase
import org.example.project.presentation.auth.models.AuthUiEvent
import org.example.project.presentation.auth.models.LoginUiState
import org.example.project.presentation.common.UiText
import org.example.project.presentation.common.asUiText

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val loginUseCase: LoginUseCase,
): ViewModel() {
    private val _loginUiState = MutableStateFlow(
        LoginUiState( userName = "",
            password = "", isLoginButtonActive = false,
            error = UiText.DynamicString(""))
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
                    is AuthResult.Success -> {
                        _authUiEvent.emit(AuthUiEvent.AuthSuccessEvent)
                    }

                    is AuthResult.Error -> {
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