package org.example.project.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.project.feature.auth.presentation.login.LoginScreen
import org.example.project.feature.auth.presentation.register.RegistrationScreen
import org.example.project.feature.course_detail.CourseDetailViewModel
import org.example.project.feature.course_detail.CourseDetailsCard
import org.example.project.app.MainScreen
import org.example.project.feature.onboarding.presentation.Boarding
import org.example.project.feature.onboarding.presentation.Splash
import org.example.project.feature.search.SearchScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavigationGraph(
    navigationViewModel: NavigationViewModel = koinViewModel<NavigationViewModel>()
) {
    val navController = rememberNavController()
    val startDestination by navigationViewModel.navigationState.collectAsStateWithLifecycle(
        Splash
    )

    fun navigateToAndPopAll(to: Destination) {
        navController.navigate(to) {
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Splash,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = innerPadding.calculateTopPadding())
        ) {
            composable<Splash> {
                Splash (
                    onDelayFinished = {  navigateToAndPopAll(startDestination) }
                )
            }

            composable<OnBoarding> {
                Boarding(
                    navigateToLogin = { navController.navigate(Login) },
                    setBoardingViewed = { navigationViewModel.setOnBoardingViewed() }
                )
            }

            composable<Login> {
                LoginScreen(
                    onBack = { navController.navigateUp() },
                    navigateToMain = { navigateToAndPopAll(Main) },
                    navigateToRegistration = { navController.navigate(Registration) }
                )
            }

            composable<Registration> {
                RegistrationScreen(
                    onBack = { navController.navigateUp() },
                    navigateToMain = { navigateToAndPopAll(Main) },
                    navigateToLogin = { navController.navigateUp() }
                )
            }

            composable<Main> {
                MainScreen(
                    navigateToSearch = { navController.navigate(Search) },
                    navigateToCourseDetail = { navController.navigate(CourseDetail(it)) },
                    logout = {
                        navigateToAndPopAll(Login)
                        navController.popBackStack()
                    }
                )
            }

            composable<Search> {
                SearchScreen(
                    navigateToMain = { navController.navigateUp() },
                    navigateToCourseDetail = { navController.navigate(CourseDetail(it)) },
                )
            }

            composable<CourseDetail> {
                val id = it.toRoute<CourseDetail>().courseId
                CourseDetailsCard(
                    onStartLessonClick = {},
                    navigateToMain = { navController.navigateUp() },
                    courseId = id,
                    courseViewModel = koinViewModel<CourseDetailViewModel>(parameters = {
                        parametersOf(id)
                    })
                )
            }
        }
    }
}