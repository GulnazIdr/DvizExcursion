package org.example.project.presentation.onboarding

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import org.example.project.presentation.navigation.BoardingNavigationGraph
import org.example.project.presentation.navigation.OnBoarding1
import org.example.project.presentation.navigation.OnBoarding2
import org.example.project.presentation.onboarding.components.RowNavigationCircle

@Composable
fun Boarding(
    navigateToLogin: () -> Unit,
){
    val navController = rememberNavController()
    var activeIndex by rememberSaveable{ mutableIntStateOf(1) }

    Scaffold(
        bottomBar = {
            RowNavigationCircle(
                activeIndex = activeIndex,
                navigateToBoarding1 = {
                    activeIndex = 1
                    navController.navigate(OnBoarding1)},
                navigateToBoarding2 = {
                    activeIndex = 2
                    navController.navigate(OnBoarding2)}
            )

            Spacer(modifier = Modifier.height(200.dp))
        }
    ) { paddingValues ->
        BoardingNavigationGraph(
            paddingValues = paddingValues,
            navigateToLogin = navigateToLogin,
            navController = navController
        )
    }
}