package org.example.project.presentation.auth

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
import org.example.project.domain.auth.User
import org.example.project.domain.auth.registration.RegisterRepository
import org.example.project.domain.auth.registration.ValidationUtil
import org.example.project.presentation.auth.models.AuthUiEvent
import org.example.project.presentation.auth.models.RegistrationUiState
import org.example.project.presentation.common.asUiText

class RegistrationViewmodel(
    private val registerRepository: RegisterRepository
): ViewModel() {

    private val _registerUiState = MutableStateFlow(
        RegistrationUiState( 
            userName = "", password = "", email = "",
            isLoginButtonActive = false, nameError = null, pswdError = null,
            emailError =  null, registerError = null)
    )
    val registerUiState: StateFlow<RegistrationUiState> = _registerUiState.asStateFlow()

    private val _authUiEvent = MutableSharedFlow<AuthUiEvent>()
    val authUiEvent: SharedFlow<AuthUiEvent> = _authUiEvent.asSharedFlow()

    fun onUserNameChanged(userName: String){
        _registerUiState.update { state ->
            val nameError = ValidationUtil.validateName(userName)?.asUiText()
            _registerUiState.value.copy(
                userName = userName,
                isLoginButtonActive = nameError == null && state.password.isNotEmpty()
                        && state.email.isNotEmpty(),
                nameError = nameError,
                pswdError = state.pswdError,
                emailError = state.emailError
            )
        }
    }

    fun onPasswordChanged(password: String){
        _registerUiState.update { state ->
            val pswdError = ValidationUtil.validatePassword(password)?.asUiText()
            _registerUiState.value.copy(
                password = password,
                isLoginButtonActive = state.userName.isNotEmpty() &&
                        pswdError == null && state.email.isNotEmpty(),
                nameError = state.nameError,
                pswdError = pswdError,
                emailError = state.emailError
            )
        }
    }

    fun onEmailChanged(email: String){
        _registerUiState.update { state ->
            val emailError = ValidationUtil.validateEmail(email)?.asUiText()
            _registerUiState.value.copy(
                email = email,
                isLoginButtonActive = state.userName.isNotEmpty() &&
                        state.password.isNotEmpty() && emailError == null,
                nameError = state.nameError,
                pswdError = state.pswdError,
                emailError = emailError
            )
        }
    }

    fun register() {
        val registerState = _registerUiState.value
        viewModelScope.launch {
            when (val res = registerRepository.signup(User(
                registerState.userName, registerState.email, registerState.password
            ))) {
                is AuthResult.Success -> {
                    _authUiEvent.emit(AuthUiEvent.AuthSuccessEvent)
                }

                is AuthResult.Error -> {
                    _registerUiState.update {
                        _registerUiState.value.copy(
                            registerError = res.error.asUiText()
                        )
                    }
                }
            }
        }
    }
}