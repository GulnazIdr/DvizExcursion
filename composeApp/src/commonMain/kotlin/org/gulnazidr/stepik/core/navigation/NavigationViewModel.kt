package org.gulnazidr.stepik.core.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.gulnazidr.stepik.core.domain.auth.TokenDataRepository
import org.gulnazidr.stepik.feature.onboarding.data.source.BoardingDataStore

class NavigationViewModel(
    private val boardingDataStore: BoardingDataStore,
    private val tokenDataRepository: TokenDataRepository
) : ViewModel() {
    private val onBoardingViewed = boardingDataStore.getOnBoardingViewed()
    private val isLoggedWithToken = tokenDataRepository.getAccessToken() != null

    private val _navigationState = MutableStateFlow<Destination>(Splash)
    val navigationState: StateFlow<Destination> = _navigationState.asStateFlow()

    init {
        navigateTo()
    }

    fun setOnBoardingViewed(){
        viewModelScope.launch {
            boardingDataStore.setOnBoardingViewed()
        }
    }

    private fun navigateTo() {
        viewModelScope.launch {
            onBoardingViewed.onSuccess { boardingViewedFlow ->
                boardingViewedFlow.collect { isViewed ->
                    _navigationState.value = when {
                        !isViewed && !isLoggedWithToken -> OnBoarding
                        isViewed && !isLoggedWithToken -> Login
                        isViewed -> Main
                        else -> Main
                    }
                }
            }.onFailure {
                _navigationState.value = OnBoarding
            }
        }
    }
}