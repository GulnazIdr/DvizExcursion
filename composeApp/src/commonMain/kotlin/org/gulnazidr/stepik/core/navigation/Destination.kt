package org.gulnazidr.stepik.core.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object OnBoarding: Destination

@Serializable
object Splash: Destination

@Serializable
object Login: Destination

@Serializable
object Auth: Destination

@Serializable
object Registration: Destination

@Serializable
object Main: Destination

@Serializable
object Search: Destination

@Serializable
data class CourseDetail(val courseId: Int): Destination