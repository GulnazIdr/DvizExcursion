package org.example.project.feature.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class BottomNavState(val navHostController: NavHostController) {
    fun navigateTo(route: String){
        navHostController.navigate(route){
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberNavState(
    navHostController: NavHostController = rememberNavController()
): BottomNavState {
    return BottomNavState(navHostController)
}