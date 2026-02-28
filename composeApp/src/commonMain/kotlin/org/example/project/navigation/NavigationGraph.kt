package org.example.project.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.LoginScreen
import org.example.project.MainScreen
import org.example.project.OnBoarding

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = OnBoarding
    ){
        composable<OnBoarding>{
            OnBoarding(
                navigateToLogin = {navController.navigate(Login)}
            )
        }

        composable<Login>{
            LoginScreen(
                onBack = {navController.navigateUp()},
                navigateToMain = {navController.navigate(Main)}
            )
        }

        composable<Main> {
            MainScreen(

            )
        }
    }
}