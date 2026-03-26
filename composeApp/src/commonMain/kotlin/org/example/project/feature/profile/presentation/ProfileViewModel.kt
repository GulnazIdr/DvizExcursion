package org.example.project.feature.profile.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.project.core.common.result.FetchDataResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.ui_logic.UiText
import org.example.project.core.designsystem.ui_logic.ValidationUtil
import org.example.project.core.designsystem.ui_logic.mapper.UserToUserUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.UserUiToUserMapper
import org.example.project.core.designsystem.ui_logic.mapper.asUiText
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi.Error
import org.example.project.core.designsystem.ui_logic.result.FetchResultUi.Success
import org.example.project.core.model.User
import org.example.project.feature.auth.presentation.models.UserUi
import org.example.project.feature.profile.domain.FetchCurrentUserUseCase
import org.example.project.feature.profile.domain.LogoutUseCase
import org.example.project.feature.profile.domain.UpdateUserUseCase

class ProfileViewModel(
    private val updateUserUseCase: UpdateUserUseCase,
    private val fetchCurrentUserUseCase: FetchCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val userMapper: UserUiToUserMapper,
    private val userUiToUserMapper: UserToUserUiMapper
) : ViewModel(){
    private var fetchJob: Job? = null
    private val _errorMessage = mutableStateOf("")
    val errorMessage = _errorMessage
    private val _currentUserInfo: UserUi? = null

    private val _profileUiState = MutableStateFlow(
        ProfileUiState(
            userName = "", email = "", phone = "", bio = "", details = "", isSaveButtonActive = false,
            nameError = null, emailError = null, phoneError = null
        )
    )
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    private val _profileFetchedState = MutableStateFlow<FetchResultUi<UserUi>>(FetchResultUi.Loading())
    val profileFetchedState: StateFlow<FetchResultUi<UserUi>> = _profileFetchedState.asStateFlow()

    init {
        getUser()
    }

    fun updateUser(){
        val userUi = _currentUserInfo?.copy(
            name = _profileUiState.value.userName,
            email = _profileUiState.value.email,
            phone = _profileUiState.value.phone,
            shortBio = _profileUiState.value.bio,
            details = _profileUiState.value.details
        )

//        viewModelScope.launch {
//            updateUserUseCase(
//                userMapper.map(userUi)
//            )
//        }
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

    fun retry(){
        getUser()
    }

    private fun getUser(){
        fetchJob = viewModelScope.launch {
            when (val result = fetchCurrentUserUseCase()) {
                is FetchDataResult.Success<User> -> {
                    val userUi = userUiToUserMapper.map(result.data)

                    _profileUiState.update { state ->
                        state.copy(
                            userName = userUi.name,
                            email = userUi.email,
                            phone = userUi.phone,
                            bio = userUi.shortBio,
                            details = userUi.details,

                        )
                    }
                    _profileFetchedState.value = Success(userUi)
                }

                is FetchDataResult.Error -> {
                    _profileFetchedState.value = Error(
                        message = result.error?.asUiText() ?: NetworkError.UNKNOWN.asUiText()
                    )
                }

                is FetchDataResult.Cache -> {
                    _profileFetchedState.value = Error(
                        message = result.error?.asUiText() ?: UiText.DynamicString(""),
                        cacheData = userUiToUserMapper.map(result.cacheData)
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