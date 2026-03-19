package org.example.project.core.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import org.example.project.feature.onboarding.domain.DataStoreRepository

class NavigationViewModel(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    private val onBoardingViewed = dataStoreRepository.getOnBoardingViewed()
    private val loggedInState = dataStoreRepository.getLoggedInState()
    fun setOnBoardingViewed(){
        viewModelScope.launch {
            dataStoreRepository.setOnBoardingViewed()
        }
    }

    val navigationState = loggedInState.combine(onBoardingViewed){ isLoggedIn, isViewed ->
        Napier.e("infor $isLoggedIn $isViewed")
        when{
            isViewed && isLoggedIn -> Main
            isViewed && !isLoggedIn -> Login
            !isViewed && !isLoggedIn -> OnBoarding
            else -> Main
        }
    }
}