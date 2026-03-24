package org.example.project.core.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.core.designsystem.components.BottomDestinationItems
import org.example.project.feature.course_catalog.CourseCatalogScreen
import org.example.project.feature.notification.NotificationScreen
import org.example.project.core.navigation.CustomNavState
import org.example.project.feature.profile.presentation.ProfileScreen

@Composable
fun BottomNavigationGraph(
    navigateToSearch: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    logout: () -> Unit,
    navController: CustomNavState,
    startDestination: BottomDestinationItems
) {
    NavHost(
        navController = navController.navHostController,
        startDestination = startDestination.route
    ) {
        composable(BottomDestinationItems.HOME.route) {
            CourseCatalogScreen(
                navigateToSearch = navigateToSearch,
                navigateToCourseDetail = navigateToCourseDetail
            )
        }

        composable(BottomDestinationItems.PROFILE.route) {
            ProfileScreen(
                onLogout = logout
            )
        }

        composable(BottomDestinationItems.NOTIFICATION.route) {
            NotificationScreen()
        }
    }
}