package org.gulnazidr.stepik.core.navigation.bottom

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.gulnazidr.stepik.core.designsystem.components.BottomDestinationItems
import org.gulnazidr.stepik.core.navigation.CustomNavState
import org.gulnazidr.stepik.feature.course_catalog.domain.FetchCoursesUseCase
import org.gulnazidr.stepik.feature.course_catalog.presentation.CourseCatalogScreen
import org.gulnazidr.stepik.feature.course_catalog.presentation.CourseViewModel
import org.gulnazidr.stepik.feature.notification.NotificationScreen
import org.gulnazidr.stepik.feature.profile.domain.FetchCurrentUserUseCase
import org.gulnazidr.stepik.feature.profile.presentation.ProfileScreen
import org.gulnazidr.stepik.feature.profile.presentation.ProfileViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

@Composable
fun BottomNavigationGraph(
    navigateToSearch: () -> Unit,
    navigateToCourseDetail: (id: Int) -> Unit,
    logout: () -> Unit,
    navController: CustomNavState,
    userScope: Scope,
    startDestination: BottomDestinationItems
) {
    NavHost(
        navController = navController.navHostController,
        startDestination = startDestination.route
    ) {
        composable(BottomDestinationItems.HOME.route) {
            CourseCatalogScreen(
                navigateToSearch = navigateToSearch,
                navigateToCourseDetail = navigateToCourseDetail,
                logout = logout,
                courseViewModel = koinViewModel<CourseViewModel>{
                    parametersOf(userScope.get<FetchCoursesUseCase>())
                }
            )
        }

        composable(BottomDestinationItems.PROFILE.route) {
            ProfileScreen(
                onLogout = logout,
                profileViewModel = koinViewModel<ProfileViewModel>{
                    parametersOf(userScope.get<FetchCurrentUserUseCase>())
                }
            )
        }

        composable(BottomDestinationItems.NOTIFICATION.route) {
            NotificationScreen()
        }
    }
}