package org.gulnazidr.stepik.feature.course_detail.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DescriptionBlock(
    modifier: Modifier = Modifier,
    title: StringResource,
    textColor: Color,
    descr: @Composable () -> Unit
){
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.bodyLarge
            .copy(
                fontWeight = FontWeight.Medium,
                color = textColor
            ),
        modifier = modifier
    )

    descr()
}