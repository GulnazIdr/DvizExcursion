package org.example.project.core.navigation.bottom

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.core.designsystem.components.BottomDestinationItems
import org.example.project.feature.course_catalog.CourseCatalogScreen
import org.example.project.feature.notification.NotificationScreen
import org.example.project.feature.onboarding.navigation.BottomNavState
import org.example.project.feature.profile.ProfileScreen

@Composable
fun BottomNavigationGraph(
    navigateToSearch: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    logout: () -> Unit,
    navController: BottomNavState,
    startDestination: BottomDestinationItems
) {
    NavHost(
        navController = navController.navHostController,
        startDestination = startDestination.route
    ) {
        BottomDestinationItems.entries.forEach { destinationItems ->
            composable(destinationItems.route) {
                when (destinationItems) {
                    BottomDestinationItems.HOME -> {
                        CourseCatalogScreen(
                            navigateToSearch = navigateToSearch,
                            navigateToCourseDetail = navigateToCourseDetail
                        )
                    }

                    BottomDestinationItems.PROFILE -> {
                        ProfileScreen(
                            onLogout = logout
                        )
                    }

                    BottomDestinationItems.NOTIFICATION -> {
                        NotificationScreen()
                    }
                }
            }
        }
    }
}