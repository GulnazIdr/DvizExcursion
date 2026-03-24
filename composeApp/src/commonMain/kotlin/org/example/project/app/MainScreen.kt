package org.example.project.app

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.example.project.core.designsystem.components.BottomDestinationItems
import org.example.project.core.designsystem.components.BottomNavigationBar
import org.example.project.core.navigation.bottom.BottomNavigationGraph
import org.example.project.core.navigation.rememberNavState

@Composable
fun MainScreen(
    navigateToSearch: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    logout: () -> Unit,
){
    val startDestination = BottomDestinationItems.HOME
    val navController = rememberNavState()

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                startDestination = startDestination
            )
        }
    ) { innerPadding ->
        BottomNavigationGraph(
            navigateToSearch = navigateToSearch,
            navigateToCourseDetail = navigateToCourseDetail,
            logout = logout,
            navController = navController,
            startDestination = startDestination
        )
    }
}

