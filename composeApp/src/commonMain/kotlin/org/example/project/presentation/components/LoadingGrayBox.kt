package org.example.project.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun LoadingGrayBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
)