package org.example.project.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.feature.auth.presentation.login.LoginScreen
import org.example.project.feature.auth.presentation.register.RegistrationScreen
import org.example.project.feature.main.presentation.MainScreen
import org.example.project.feature.onboarding.Boarding

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
            Boarding(
                navigateToLogin = { navController.navigate(Login) },
            )
        }

        composable<Login>{
            LoginScreen(
                onBack = { navController.navigateUp() },
                navigateToMain = { navigateAndPopAll(Main) },
                navigateToRegistration = { navController.navigate(Registration) }
            )
        }

        composable<Registration>{
            RegistrationScreen(
                onBack = { navController.navigateUp() },
                navigateToMain = { navigateAndPopAll(Main) },
                navigateToLogin = { navController.navigateUp() }
            )
        }

        composable<Main> {
            MainScreen()
        }
    }
}