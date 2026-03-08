package org.example.project.feature.onboarding.navigation

import kotlinx.serialization.Serializable

interface BoardingDestination

@Serializable
object OnBoarding1: BoardingDestination

@Serializable
object OnBoarding2: BoardingDestination
