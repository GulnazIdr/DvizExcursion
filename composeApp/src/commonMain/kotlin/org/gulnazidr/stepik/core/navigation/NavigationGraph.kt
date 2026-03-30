package org.gulnazidr.stepik.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.gulnazidr.stepik.app.MainScreen
import org.gulnazidr.stepik.feature.auth.presentation.components.AuthPage
import org.gulnazidr.stepik.feature.auth.presentation.login.LoginScreen
import org.gulnazidr.stepik.feature.auth.presentation.register.RegistrationScreen
import org.gulnazidr.stepik.feature.course_detail.presentation.CourseDetailViewModel
import org.gulnazidr.stepik.feature.course_detail.presentation.CourseDetailsCard
import org.gulnazidr.stepik.feature.onboarding.presentation.Boarding
import org.gulnazidr.stepik.feature.onboarding.presentation.Splash
import org.gulnazidr.stepik.feature.search.presentation.SearchScreen
import org.koin.compose.getKoin
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@Composable
fun NavigationGraph(
    navigationViewModel: NavigationViewModel = koinViewModel<NavigationViewModel>()
) {
    val navController = rememberNavController()
    val startDestination by navigationViewModel.navigationState.collectAsStateWithLifecycle(
        Splash
    )
    val currentBackStackEntry = navController.currentBackStackEntry
    val userScope = getKoin().createScope("userSessionScope", named("userSessionScope"))

    fun navigateAndPopAll(route: Destination){
        navController.navigate(route){
            val prev = navController.previousBackStackEntry?.destination?.route
            val current = currentBackStackEntry?.destination?.route
            if (prev != null) {
                popUpTo(prev) {
                    inclusive = true
                }
            }else if(current != null){
                popUpTo(current) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Splash,
            modifier = Modifier.padding(
                start = 20.dp, end = 20.dp,
                top = innerPadding.calculateTopPadding()
            )
        ) {
            composable<Splash> {
                Splash(
                    onDelayFinished = {
                        if (startDestination != Splash) {
                            navigateAndPopAll(startDestination)
                        }
                    }
                )
            }

            composable<OnBoarding> {
                Boarding(
                    navigateToLogin = { navigateAndPopAll(Login) },
                    setBoardingViewed = { navigationViewModel.setOnBoardingViewed() }
                )
            }

            composable<Login> {
                LoginScreen(
                    onBack = { navController.navigateUp() },
                    navigateToMain = { navigateAndPopAll(Main) },
                    navigateToRegistration = { navController.navigate(Registration) },
                    navigateToStepikAuth = { navController.navigate(Auth) },
                    isFirstInStack = navController.previousBackStackEntry == null
                )
            }

            composable<Auth> {
                AuthPage(
                    navigateToMain = { navigateAndPopAll(Main) }
                )
            }

            composable<Registration> {
                RegistrationScreen(
                    onBack = { navController.navigateUp() },
                    navigateToMain = { navigateAndPopAll(Main) },
                    navigateToLogin = { navController.navigateUp() }
                )
            }

            composable<Main> {
                MainScreen(
                    navigateToSearch = { navController.navigate(Search) },
                    navigateToCourseDetail = { navController.navigate(CourseDetail(it)) },
                    logout = {
                        navigateAndPopAll(Login)
                    },
                    userScope = userScope
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
