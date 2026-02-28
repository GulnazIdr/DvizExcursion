package org.example.project.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.zIndex

@Composable
fun BlurryCircle(
    offsetX: Dp,
    offsetY: Dp,
    size: Dp,
    color: Color
) {
    Box(
        modifier = Modifier
            .offset(offsetX, offsetY)
            .size(size)
            .clip(CircleShape)
            .background(
                Brush.radialGradient(
                    listOf(
                        color, Color.White
                    )
                )
            )
            .zIndex(0f)

    )
}