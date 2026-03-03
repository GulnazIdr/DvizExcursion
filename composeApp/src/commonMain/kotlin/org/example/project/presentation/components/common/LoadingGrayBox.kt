package org.example.project.presentation.components.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.dp

@Composable
fun LoadingGrayBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
){
    val height = LocalWindowInfo.current.containerDpSize.height.value

    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .height((height*0.5).dp)
    ){
        content()
    }
}