package org.example.project.core.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object OnBoarding: Destination

@Serializable
object Login: Destination

@Serializable
object Registration: Destination

@Serializable
object MainBlock: Destination{
    val route: String = "main_route"
}

@Serializable
object Main: Destination{
    val route: String = "main"
}

@Serializable
object Search: Destination

@Serializable
object CourseDetail: Destination