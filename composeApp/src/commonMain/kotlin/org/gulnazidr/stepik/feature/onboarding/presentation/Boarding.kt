package org.gulnazidr.stepik.feature.onboarding.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.gulnazidr.stepik.feature.onboarding.navigation.BoardingDestination
import org.gulnazidr.stepik.feature.onboarding.navigation.BoardingNavigationGraph
import org.gulnazidr.stepik.core.navigation.rememberNavState
import org.gulnazidr.stepik.feature.onboarding.presentation.components.RowNavigationCircle

@Composable
fun Boarding(
    navigateToLogin: () -> Unit,
    setBoardingViewed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavState()
    val startDestination = BoardingDestination.BOARDING1

    Scaffold(
        bottomBar = {
            RowNavigationCircle(
                navController = navController,
                startDestination = startDestination,
                setBoardingViewed = setBoardingViewed
            )

            Spacer(modifier = Modifier.height(200.dp))
        }
    ) { paddingValues ->
        BoardingNavigationGraph(
            navigateToLogin = navigateToLogin,
            navController = navController.navHostController,
            startDestination = startDestination,
           // modifier = Modifier.padding(paddingValues)
        )
    }
}