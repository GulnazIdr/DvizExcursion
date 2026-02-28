package org.example.project.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object OnBoarding: Destination

@Serializable
object Login: Destination

@Serializable
object Main: Destination