package org.example.project.feature.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.database.LocalCourseRepository
import org.example.project.core.database.LocalUserRepository
import org.example.project.core.designsystem.ui_logic.ValidationUtil
import org.example.project.feature.auth.presentation.mappers.asUiText
import org.example.project.feature.auth.presentation.models.UserUi
import org.example.project.feature.onboarding.domain.DataStoreRepository

class ProfileViewModel(
    private val localUserRepository: LocalUserRepository,
    private val localCourseRepository: LocalCourseRepository,
    private val dataStoreRepository: DataStoreRepository
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
            val userId = dataStoreRepository.getCurrentUserId()
            if (userId != null) {
                localUserRepository.updateUser(
                    userUi.toUser().copy(
                        id = userId
                    )
                )
            }
        }
    }

    fun onEmailChanged(email: String) {
        Napier.e("email $email")
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
            val user = localUserRepository.getUser()
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
            localUserRepository.deleteUser()
            localCourseRepository.deleteCourse()
            dataStoreRepository.deleteData()
        }
    }
}