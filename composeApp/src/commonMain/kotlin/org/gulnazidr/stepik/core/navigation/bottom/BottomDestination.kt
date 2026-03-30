package org.gulnazidr.stepik.core.navigation.bottom

import kotlinx.serialization.Serializable

interface BottomDestination

@Serializable
data object Home: BottomDestination

@Serializable
data object Profile: BottomDestination

@Serializable
data object Notification: BottomDestination