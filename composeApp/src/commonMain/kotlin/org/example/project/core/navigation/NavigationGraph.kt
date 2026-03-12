package org.example.project.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import org.example.project.feature.auth.presentation.login.LoginScreen
import org.example.project.feature.auth.presentation.register.RegistrationScreen
import org.example.project.feature.course_detail.CourseDetailsCard
import org.example.project.feature.main.presentation.CourseViewModel
import org.example.project.feature.main.presentation.MainScreen
import org.example.project.feature.onboarding.Boarding
import org.example.project.feature.onboarding.OnBoarding
import org.example.project.feature.search.SearchScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()

    fun navigateAndPopAll(to: Destination){
        navController.navigate(to){
            popUpTo(navController.graph.startDestinationId){
                inclusive = true
            }
        }
    }

    fun navigateAndPopAll(to: String){
        navController.navigate(to){
            popUpTo(navController.graph.startDestinationId){
                inclusive = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = MainBlock.route
    ){
        composable<OnBoarding>{
            Boarding(
                navigateToLogin = { navController.navigate(Login) },
            )
        }

        composable<Login>{
            LoginScreen(
                onBack = { navController.navigateUp() },
                navigateToMain = { navigateAndPopAll(MainBlock.route) },
                navigateToRegistration = { navController.navigate(Registration) }
            )
        }

        composable<Registration>{
            RegistrationScreen(
                onBack = { navController.navigateUp() },
                navigateToMain = { navigateAndPopAll(MainBlock.route) },
                navigateToLogin = { navController.navigateUp() }
            )
        }

        navigation(
            startDestination = Main.route,
            route = MainBlock.route
        ) {
            composable(Main.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(MainBlock.route)
                }
                val courseViewmodel = koinViewModel<CourseViewModel>(
                    viewModelStoreOwner = parentEntry
                )

                MainScreen(
                    navigateToSearch = { navController.navigate(Search) },
                    courseViewModel = courseViewmodel,
                    navigateToCourseDetail = {navController.navigate(CourseDetail)}
                )
            }

            composable<Search> {backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(MainBlock.route)
                }
                val courseViewmodel = koinViewModel<CourseViewModel>(
                    viewModelStoreOwner = parentEntry
                )

                SearchScreen(
                    navigateToMain = { navController.navigateUp() },
                    courseViewModel = courseViewmodel,
                    navigateToCourseDetail = {navController.navigate(CourseDetail)}
                )
            }

            composable<CourseDetail> { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(MainBlock.route)
                }
                val courseViewmodel = koinViewModel<CourseViewModel>(
                    viewModelStoreOwner = parentEntry
                )

                CourseDetailsCard(
                    onStartLessonClick = {},
                    courseViewModel = courseViewmodel,
                    navigateToMain = {navController.navigateUp()}
                )

            }
        }
    }
}