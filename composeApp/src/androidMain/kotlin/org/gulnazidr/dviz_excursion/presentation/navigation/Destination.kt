package org.gulnazidr.dviz_excursion.presentation.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object OnBoarding: Destination

@Serializable
object Login: Destination