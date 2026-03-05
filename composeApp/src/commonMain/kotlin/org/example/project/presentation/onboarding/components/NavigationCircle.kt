package org.example.project.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationCircle(
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .background(
                color =
                    if (!isActive) MaterialTheme.colorScheme.onSecondary
                    else MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .size(25.dp)
            .clickable(onClick = onClick )
    )
}