package org.example.project.core.designsystem.components

import org.jetbrains.compose.resources.DrawableResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.home
import stepik.composeapp.generated.resources.notification
import stepik.composeapp.generated.resources.profile

enum class BottomDestinationItems(
    val route: String,
    val iconResource: DrawableResource,
    val contentDescription: String
) {
    HOME("Home",  Res.drawable.home, "Home"),
    PROFILE("Profile", Res.drawable.profile, "Profile"),
    NOTIFICATION("Notification", Res.drawable.notification, "Notification")
}