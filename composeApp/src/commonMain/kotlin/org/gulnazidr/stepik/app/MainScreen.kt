package org.gulnazidr.stepik.app

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.gulnazidr.stepik.core.designsystem.components.BottomDestinationItems
import org.gulnazidr.stepik.core.designsystem.components.BottomNavigationBar
import org.gulnazidr.stepik.core.navigation.bottom.BottomNavigationGraph
import org.gulnazidr.stepik.core.navigation.rememberNavState
import org.koin.core.scope.Scope

@Composable
fun MainScreen(
    navigateToSearch: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    userScope: Scope,
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
            startDestination = startDestination,
            userScope = userScope
        )
    }
}

