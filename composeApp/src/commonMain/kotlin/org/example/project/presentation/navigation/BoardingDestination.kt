package org.example.project.presentation.navigation

import kotlinx.serialization.Serializable

interface BoardingDestination

@Serializable
object OnBoarding1: BoardingDestination

@Serializable
object OnBoarding2: BoardingDestination
