package org.example.project.components

import androidx.compose.animation.core.EaseInSine
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedBorderCard(
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit

) {
    val colors =
        listOf(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.onPrimary,
            Color.White,
            MaterialTheme.colorScheme.onPrimary,
            Color.White,
            MaterialTheme.colorScheme.onPrimary,
            Color.White,
            MaterialTheme.colorScheme.onPrimary,
            MaterialTheme.colorScheme.onPrimary,
            Color.White,
            MaterialTheme.colorScheme.primaryContainer
        )

    val brush = Brush.sweepGradient(colors)

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val angle by
    infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(15000, easing = EaseInSine),
                repeatMode = RepeatMode.Restart
            ),
        label = ""
    )

    Box(modifier = modifier){
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {
            rotate(degrees = angle) {
                drawCircle(
                    brush = brush,
                    radius = size.width*2,
                    blendMode = BlendMode.Lighten
                )
            }

            drawRoundRect(
                color = backgroundColor,
                topLeft = Offset(13.dp.toPx(), 10.dp.toPx()),
                size = Size(
                    width = size.width - 25.dp.toPx(),
                    height = size.height - 20.dp.toPx()
                ),
                cornerRadius = CornerRadius(80f, 80f)
            )
        }

        Box(
            modifier = Modifier.align(Alignment.Center)
        ) {
            content()
        }
    }

}

@Preview
@Composable
private fun AnimatedBorderCardPrev() {
    AnimatedBorderCard(
        content = {}
    )
}