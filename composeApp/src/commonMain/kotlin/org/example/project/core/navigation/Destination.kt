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
object Main: Destination

@Serializable
object Search: Destination

@Serializable
class CourseDetail(val courseId: Int): Destination