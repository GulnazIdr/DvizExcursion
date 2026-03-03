package org.example.project.presentation.main.post

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PostIcon(
    iconResource: DrawableResource,
    modifier: Modifier = Modifier
){
    Icon(
        painter = painterResource(iconResource),
        contentDescription = "icon",
        modifier = modifier.size(20.dp),
        tint = MaterialTheme.colorScheme.onSecondaryContainer
    )
}