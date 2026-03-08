package org.example.project.core.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun LoadingGrayBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
)