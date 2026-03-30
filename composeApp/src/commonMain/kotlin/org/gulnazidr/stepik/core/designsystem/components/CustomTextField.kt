package org.gulnazidr.stepik.core.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isEnabled: Boolean = true,
    textColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
    visualTransformation: VisualTransformation = VisualTransformation.None
){
    BasicTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .padding(start = 15.dp),
        textStyle = MaterialTheme.typography.bodyMedium.copy(
            color = textColor
        ),
        singleLine = true,
        visualTransformation = visualTransformation,
        cursorBrush = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.colorScheme.tertiary
            )
        ),
        enabled = isEnabled
    )
}