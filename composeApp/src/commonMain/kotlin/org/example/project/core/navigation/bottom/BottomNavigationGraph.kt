package org.example.project.core.navigation.bottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.feature.course_catalog.CourseCatalogScreen
import org.example.project.core.designsystem.components.BottomDestinationItems
import org.example.project.feature.notification.NotificationScreen
import org.example.project.feature.onboarding.navigation.BottomNavState
import org.example.project.feature.profile.ProfileScreen

@Composable
fun BottomNavigationGraph(
    navigateToSearch: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    logout: () -> Unit,
    navController: BottomNavState,
    startDestination: BottomDestinationItems,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController.navHostController,
        startDestination = startDestination.route,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
            .padding(top = paddingValues.calculateTopPadding(), start = paddingValues.calculateStartPadding(
                LayoutDirection.Ltr), end = paddingValues.calculateEndPadding(LayoutDirection.Rtl))
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