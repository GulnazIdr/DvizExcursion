package org.gulnazidr.stepik.feature.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gulnazidr.stepik.core.common.result.FetchDataResult
import org.gulnazidr.stepik.core.common.result.UserNetworkError
import org.gulnazidr.stepik.core.designsystem.ui_logic.ValidationUtil
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.UserToUserUiMapper
import org.gulnazidr.stepik.core.designsystem.ui_logic.mapper.asUiText
import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi
import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi.Error
import org.gulnazidr.stepik.core.designsystem.ui_logic.result.FetchResultUi.Success
import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.feature.auth.presentation.models.UserUi
import org.gulnazidr.stepik.feature.profile.domain.FetchCurrentUserUseCase
import org.gulnazidr.stepik.feature.profile.domain.LogoutUseCase
import org.gulnazidr.stepik.feature.profile.presentation.models.ProfileFetchUiState
import org.gulnazidr.stepik.feature.profile.presentation.models.ProfileUiState

class ProfileViewModel(
    private val fetchCurrentUserUseCase: FetchCurrentUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val userUiToUserMapper: UserToUserUiMapper
) : ViewModel() {
    private val _profileLogoutEvent = MutableSharedFlow<Boolean>()
    val profileLogoutEvent: SharedFlow<Boolean> = _profileLogoutEvent.asSharedFlow()
    private var fetchJob: Job? = null

    private val _profileUiState = MutableStateFlow(
        ProfileUiState(
            userName = "", email = "", phone = "", bio = "", details = "",
            isSaveButtonActive = false, nameError = null, emailError = null, phoneError = null
        )
    )
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    private val _profileFetchedUiState = MutableStateFlow<ProfileFetchUiState>(
        ProfileFetchUiState(
            isRefreshing = false,
            userFetchedState = FetchResultUi.Loading()
        )
    )
    val profileFetchedUiState: StateFlow<ProfileFetchUiState> = _profileFetchedUiState.asStateFlow()

    init {
        getUser()
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

    fun retry() {
        _profileFetchedUiState.update { state ->
            state.copy(
                isRefreshing = true
            )
        }
        if (fetchJob != null) {
            fetchJob?.cancel()
        }
        getUser(true)
    }

    private fun getUser(isRefreshing: Boolean = false) {
        fetchJob = viewModelScope.launch {
            when (val result = fetchCurrentUserUseCase(isRefreshing)) {
                is FetchDataResult.Success<User> -> {
                    val userUi = userUiToUserMapper.map(result.data)

                    _profileFetchedUiState.update { state ->
                        state.copy(
                            isRefreshing = false,
                            userFetchedState = Success(userUi)
                        )
                    }
                }

                is FetchDataResult.Error -> {
                    _profileFetchedUiState.update { state ->
                        state.copy(
                            isRefreshing = false,
                            userFetchedState =  Error(
                                message =
                                    if (result.error is UserNetworkError) {
                                        result.error.asUiText()
                                    } else {
                                        UserNetworkError.UNKNOWN.asUiText()
                                    }
                            )
                        )
                    }
                }

                is FetchDataResult.Cache -> {
                    _profileFetchedUiState.update { state ->
                        state.copy(
                            isRefreshing = false,
                            userFetchedState =  Error(
                                message =
                                    if (result.error is UserNetworkError) {
                                        result.error.asUiText()
                                    } else {
                                        UserNetworkError.UNKNOWN.asUiText()
                                    },
                                cacheData = userUiToUserMapper.map(result.cacheData)
                            )
                        )
                    }
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            val logoutRes = logoutUseCase()
            if (logoutRes){
                _profileLogoutEvent.emit(true)
            }
        }
    }

    fun setInitialUserData(userUi: UserUi) {
        _profileUiState.update { state ->
            state.copy(
                userName = userUi.name,
                email = userUi.email,
                phone = userUi.phone,
                bio = userUi.shortBio,
                details = userUi.details
            )
        }
    }
}