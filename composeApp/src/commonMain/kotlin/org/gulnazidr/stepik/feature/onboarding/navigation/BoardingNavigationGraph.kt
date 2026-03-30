package org.gulnazidr.stepik.feature.onboarding.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.gulnazidr.stepik.feature.onboarding.presentation.OnBoarding
import org.gulnazidr.stepik.feature.onboarding.presentation.OnBoarding2

@Composable
fun BoardingNavigationGraph(
    navigateToLogin: () -> Unit,
    navController: NavHostController,
    startDestination: BoardingDestination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    ) {
        BoardingDestination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    BoardingDestination.BOARDING1 -> {
                        OnBoarding()
                    }

                    BoardingDestination.BOARDING2 -> {
                        OnBoarding2(
                            navigateToLogin = navigateToLogin
                        )
                    }
                }

            }
        }
    }
}