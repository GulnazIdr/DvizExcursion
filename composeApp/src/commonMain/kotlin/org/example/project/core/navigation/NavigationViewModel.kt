package org.example.project.core.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.example.project.core.database.source.LocalUserRepository
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.onboarding.data.source.BoardingDataStore

class NavigationViewModel(
    private val boardingDataStore: BoardingDataStore,
    private val tokenDataRepository: TokenDataRepository
) : ViewModel() {
    private val onBoardingViewed = boardingDataStore.getOnBoardingViewed()
    private val isLoggedWithToken = tokenDataRepository.getAccessToken() != null

    fun setOnBoardingViewed(){
        viewModelScope.launch {
            boardingDataStore.setOnBoardingViewed()
        }
    }

    val navigationState = flowOf(isLoggedWithToken).combine(onBoardingViewed){ isLoggedIn, isViewed ->
        when{
            !isViewed && !isLoggedIn  -> OnBoarding
            isViewed && !isLoggedIn-> Login
            isViewed -> Main
            else -> Main
        }
    }
}