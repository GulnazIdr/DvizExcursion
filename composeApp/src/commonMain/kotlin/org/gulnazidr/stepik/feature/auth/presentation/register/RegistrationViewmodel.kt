package org.gulnazidr.stepik.feature.auth.presentation.register

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
import org.gulnazidr.stepik.core.designsystem.ui_logic.ValidationUtil
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.UserUiToUserMapper
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.asUiText
import org.gulnazidr.stepik.feature.auth.domain.result.AuthResult
import org.gulnazidr.stepik.feature.auth.domain.registration.RegisterUseCase
import org.gulnazidr.stepik.feature.auth.presentation.models.AuthUiEvent
import org.gulnazidr.stepik.feature.auth.presentation.models.RegistrationUiState
import org.gulnazidr.stepik.feature.auth.presentation.models.UserUi

class RegistrationViewmodel(
    private val registerUseCase: RegisterUseCase,
    private val userMapper: UserUiToUserMapper
) : ViewModel() {
    private val _registerUiState = MutableStateFlow(
        RegistrationUiState(
            userName = "", password = "", email = "", isLoginButtonActive = false,
            isPolicyChecked = false, nameError = null, pswdError = null, emailError = null,
            registerError = null, policyError = null
        )
    )
    val registerUiState: StateFlow<RegistrationUiState> = _registerUiState.asStateFlow()

    private val _authUiEvent = MutableSharedFlow<AuthUiEvent>()
    val authUiEvent: SharedFlow<AuthUiEvent> = _authUiEvent.asSharedFlow()

    fun onUserNameChanged(userName: String) {
        _registerUiState.update { state ->
            val nameError = ValidationUtil.validateName(userName)?.asUiText()
            _registerUiState.value.copy(
                userName = userName,
                isLoginButtonActive = nameError == null && state.password.isNotEmpty() &&
                        state.isPolicyChecked && state.email.isNotEmpty(),
                nameError = nameError,
                pswdError = state.pswdError,
                emailError = state.emailError
            )
        }
    }

    fun onPasswordChanged(password: String) {
        _registerUiState.update { state ->
            val pswdError = ValidationUtil.validatePassword(password)?.asUiText()
            _registerUiState.value.copy(
                password = password,
                isLoginButtonActive = state.userName.isNotEmpty() && state.isPolicyChecked &&
                        pswdError == null && state.email.isNotEmpty(),
                nameError = state.nameError,
                pswdError = pswdError,
                emailError = state.emailError
            )
        }
    }

    fun onEmailChanged(email: String) {
        _registerUiState.update { state ->
            val emailError = ValidationUtil.validateEmail(email)?.asUiText()
            _registerUiState.value.copy(
                email = email,
                isLoginButtonActive = state.userName.isNotEmpty() && state.isPolicyChecked &&
                        state.password.isNotEmpty() && emailError == null,
                nameError = state.nameError,
                pswdError = state.pswdError,
                emailError = emailError
            )
        }
    }

    fun onPolicyChecked(isChecked: Boolean) {
        _registerUiState.update { state ->
            val policyError = ValidationUtil.validatePolicy(isChecked)?.asUiText()
            _registerUiState.value.copy(
                isPolicyChecked = isChecked,
                isLoginButtonActive = isChecked && state.userName.isNotEmpty() &&
                        state.password.isNotEmpty() && state.emailError == null,
                nameError = state.nameError,
                pswdError = state.pswdError,
                emailError = state.emailError,
                policyError = policyError
            )
        }
    }

    fun register() {
        val registerState = _registerUiState.value
        viewModelScope.launch {
            val userUi = UserUi(
                name = registerState.userName,
                password = registerState.password,
                email = registerState.email
            )
            when (val res = registerUseCase(userMapper.map(userUi))) {
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