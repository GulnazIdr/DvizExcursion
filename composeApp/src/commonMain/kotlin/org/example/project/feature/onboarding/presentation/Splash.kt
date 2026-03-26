package org.example.project.feature.onboarding.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.stepik_logo

@Composable
fun Splash(
    modifier: Modifier = Modifier,
    onDelayFinished: () -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onDelayFinished()
    }

    Box(
        modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.stepik_logo),
            contentDescription = "stepik image",
            tint = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.size(70.dp)
        )
    }
}