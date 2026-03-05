package org.example.project.presentation.auth.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.redidit_circle
import org.jetbrains.compose.resources.painterResource

@Composable
fun RedditLogo(
    size: Dp,
    modifier: Modifier = Modifier
){
    Icon(
        painter = painterResource(Res.drawable.redidit_circle),
        contentDescription = "reddit logo icon",
        modifier = modifier.size(size),
        tint = Color.Unspecified
    )
}