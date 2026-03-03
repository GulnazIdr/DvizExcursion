package org.example.project.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.presentation.onboarding.OnBoarding
import org.example.project.presentation.onboarding.OnBoarding2

@Composable
fun BoardingNavigationGraph(
    navigateToLogin: () -> Unit,
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = OnBoarding1
    ){
        composable<OnBoarding1>{
            OnBoarding()
        }

        composable<OnBoarding2>{
            OnBoarding2(
                navigateToLogin = navigateToLogin,
                paddingValues = paddingValues
            )
        }
    }
}