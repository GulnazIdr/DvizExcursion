package org.example.project.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.presentation.login.LoginScreen
import org.example.project.presentation.main.MainScreen
import org.example.project.presentation.onboarding.Boarding

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    fun navigateAndPopAll(to: Destination){
        navController.navigate(to){
            popUpTo(navController.graph.startDestinationId){
                inclusive = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = OnBoarding
    ){
        composable<OnBoarding>{
            Boarding (
                navigateToLogin = {navController.navigate(Login)},
            )
        }

        composable<Login>{
            LoginScreen(
                onBack = { navController.navigateUp() },
                navigateToMain = { navigateAndPopAll(Main) }
            )
        }

        composable<Main> {
            MainScreen()
        }
    }
}