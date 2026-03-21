package org.example.project.core.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.onboarding.domain.DataStoreRepository

class NavigationViewModel(
    private val dataStoreRepository: DataStoreRepository,
    private val tokenDataRepository: TokenDataRepository
) : ViewModel() {
    private val onBoardingViewed = dataStoreRepository.getOnBoardingViewed()
    private val isLoggedWithToken = tokenDataRepository.getToken() != null
    private val stepikLoggedInState = dataStoreRepository.getCurrentUserId().map {
        it != null
    }

    fun setOnBoardingViewed(){
        viewModelScope.launch {
            dataStoreRepository.setOnBoardingViewed()
        }
    }

    val navigationState = stepikLoggedInState.combine(onBoardingViewed){ isLoggedIn, isViewed ->
        when{
            !isViewed && (!isLoggedIn && !isLoggedWithToken)  -> OnBoarding
            isViewed && (!isLoggedIn && !isLoggedWithToken) -> Login
            isViewed -> Main
            else -> Main
        }
    }
}