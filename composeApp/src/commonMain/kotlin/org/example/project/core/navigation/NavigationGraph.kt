package org.example.project.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
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

    NavHost(
        navController = navController,
        startDestination = Main
    ){
        composable<OnBoarding>{
            Boarding(
                navigateToLogin = { navController.navigate(Login) },
            )
        }

        composable<Login>{
            LoginScreen(
                onBack = { navController.navigateUp() },
                navigateToMain = { navigateAndPopAll(Main) },
                navigateToRegistration = { navController.navigate(Registration) }
            )
        }

        composable<Registration>{
            RegistrationScreen(
                onBack = { navController.navigateUp() },
                navigateToMain = { navigateAndPopAll(Main) },
                navigateToLogin = { navController.navigateUp() }
            )
        }


        composable<Main> {
            MainScreen(
                navigateToSearch = { navController.navigate(Search) },
                navigateToCourseDetail = {navController.navigate(CourseDetail(it))}
            )
        }

        composable<Search> {
            SearchScreen(
                navigateToMain = { navController.navigateUp() },
                navigateToCourseDetail = {navController.navigate(CourseDetail(it))}
            )
        }

        composable<CourseDetail> {
            val id = it.toRoute<CourseDetail>().courseId
            CourseDetailsCard(
                onStartLessonClick = {},
                navigateToMain = {navController.navigateUp()},
                courseId = id
            )

        }

    }
}