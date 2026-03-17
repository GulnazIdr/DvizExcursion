package org.example.project.feature.onboarding.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.feature.onboarding.presentation.OnBoarding
import org.example.project.feature.onboarding.presentation.OnBoarding2

@Composable
fun BoardingNavigationGraph(
    navigateToLogin: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = OnBoarding1,
        modifier = modifier
    ){
        composable<OnBoarding1>{
            OnBoarding()
        }

        composable<OnBoarding2>{
            OnBoarding2(
                navigateToLogin = navigateToLogin
            )
        }
    }
}