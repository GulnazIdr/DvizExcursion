package org.example.project.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

@Composable
actual fun LoadingGrayBox(
    modifier: Modifier,
    content: @Composable (() -> Unit)
){
    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(10.dp)
            )
            .width((getWindowWidth()*0.3).dp)
            .height((getWindowHeight()*0.5).dp)
    ){
        content()
    }
}