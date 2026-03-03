package org.gulnazidr.dviz_excursion.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.gulnazidr.dviz_excursion.presentation.OnBoarding
import org.example.project.navigation.Login
import org.example.project.navigation.OnBoarding
import org.gulnazidr.dviz_excursion.presentation.LoginScreen

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnBoarding
    ){
        composable<OnBoarding> {
            OnBoarding(
                navigateToLogin = { navController.navigate(Login) }
            )
        }

        composable<Login> {
            LoginScreen(
                onBack = { navController.navigateUp() }
            )
        }
    }
}