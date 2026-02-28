package org.example.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(): ViewModel() {
    private val _loginUiState = MutableStateFlow(LoginUiState(
        userName = "", password = "", isLoginButtonActive = false, error = ""
    ))
    val loginUiState: StateFlow<LoginUiState> = _loginUiState.asStateFlow()

    private val _loginUiEvent = MutableSharedFlow<LoginUiEvent>()
    val loginUiEvent: SharedFlow<LoginUiEvent> = _loginUiEvent.asSharedFlow()

    fun onUserNameChanged(userName: String){
        _loginUiState.update { state ->
            _loginUiState.value.copy(
                userName = userName,
                isLoginButtonActive = userName.isNotEmpty() && state.password.isNotEmpty(),
                error =
                    if (userName.isEmpty()) "username is empty"
                    else if (state.password.isEmpty()) "password is empty"
                    else ""
            )
        }
    }

    fun onPasswordChanged(password: String){
        _loginUiState.update { state ->
            _loginUiState.value.copy(
                password = password,
                isLoginButtonActive = password.isNotEmpty() && state.userName.isNotEmpty(),
                error =
                    if (state.userName.isEmpty()) "username is empty"
                    else if (state.password.isEmpty()) "password is empty"
                    else ""
            )
        }
    }

    fun login(){
        val loginState = _loginUiState.value
        viewModelScope.launch {
            if (loginState.password.isNotEmpty() && loginState.userName.isNotEmpty()) {
                _loginUiEvent.emit(LoginUiEvent.LoginSuccessEvent)
            }
        }
    }
}