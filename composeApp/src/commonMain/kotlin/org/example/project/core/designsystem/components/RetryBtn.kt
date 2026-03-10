package org.example.project.core.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.reddit
import dvizexcursion.composeapp.generated.resources.refresh
import org.jetbrains.compose.resources.painterResource

@Composable
fun RetryBtn(
    onRetry: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { onRetry() },
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(Res.drawable.refresh),
                contentDescription = "refresh icon",
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier .size((getWindowHeight() * 0.3).dp)
            )
        }
    }
}