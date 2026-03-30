package org.gulnazidr.stepik.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class CustomNavState(val navHostController: NavHostController) {
    fun navigateToLaunchSingle(route: String){
        navHostController.navigate(route){
            launchSingleTop = true
            restoreState = true
        }
    }

    fun navigateToPopUpTo(popUpTo: String, navigateTo: String){
        navHostController.popBackStack(popUpTo, inclusive = false)
        navigateToLaunchSingle(navigateTo)
    }
}

@Composable
fun rememberNavState(
    navHostController: NavHostController = rememberNavController()
): CustomNavState {
    return CustomNavState(navHostController)
}