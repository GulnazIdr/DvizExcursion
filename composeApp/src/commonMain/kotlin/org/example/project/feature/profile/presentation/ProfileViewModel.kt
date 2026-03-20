package org.example.project.feature.profile.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.designsystem.ui_logic.ValidationUtil
import org.example.project.core.designsystem.ui_logic.mapper.UserUiToUserMapper
import org.example.project.core.designsystem.ui_logic.mapper.asUiText
import org.example.project.feature.auth.presentation.models.UserUi
import org.example.project.feature.profile.domain.GetUserUseCase
import org.example.project.feature.profile.domain.LogoutUseCase
import org.example.project.feature.profile.domain.UpdateUserUseCase

class ProfileViewModel(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val userMapper: UserUiToUserMapper
) : ViewModel(){
    private val _errorMessage = mutableStateOf("")
    val errorMessage = _errorMessage

    private val _profileUiState = MutableStateFlow(
        ProfileUiState(
            userName = "", email = "", phone = "", isSaveButtonActive = false, nameError = null,
            emailError = null, phoneError = null
        )
    )
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    init {
        getUser()
    }

    fun updateUser(){
        val userUi = UserUi(
            name = _profileUiState.value.userName,
            password = "",
            email = _profileUiState.value.email,
            phone = _profileUiState.value.phone
        )
        viewModelScope.launch {
            updateUserUseCase(
                userMapper.map(userUi)
            )
        }
    }

    fun onEmailChanged(email: String) {
        _profileUiState.update { state ->
            val emailError = ValidationUtil.validateEmail(email)?.asUiText()
            _profileUiState.value.copy(
                email = email,
                isSaveButtonActive = state.nameError == null && state.phoneError == null && emailError == null,
                nameError = state.nameError,
                emailError = emailError,
                phoneError = state.phoneError
            )
        }
    }

    fun onNameChanged(userName: String) {
        _profileUiState.update { state ->
            val nameError = ValidationUtil.validateName(userName)?.asUiText()
            _profileUiState.value.copy(
                userName = userName,
                isSaveButtonActive = nameError == null && state.phoneError == null && state.emailError == null,
                nameError = nameError,
                emailError = state.emailError,
                phoneError = state.phoneError
            )
        }
    }

    fun onPhoneChanged(phone: String) {
        _profileUiState.update { state ->
            val phoneError = ValidationUtil.validatePhone(phone)?.asUiText()
            _profileUiState.value.copy(
                phone = phone,
                isSaveButtonActive = phoneError == null && state.nameError == null && state.emailError == null,
                nameError = state.nameError,
                emailError = state.emailError,
                phoneError = phoneError
            )
        }
    }

    private fun getUser(){
        viewModelScope.launch {
            val user = getUserUseCase()
            if (user != null){
                _profileUiState.update { state ->
                    state.copy(
                        userName = user.name,
                        phone = user.phone,
                        email = user.email
                    )
                }
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}