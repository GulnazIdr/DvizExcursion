package org.example.project.feature.course_detail.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.what_get_text
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DescriptionBlock(
    modifier: Modifier = Modifier,
    title: StringResource,
    descr: @Composable () -> Unit
){
    Text(
        text = stringResource(title),
        style = MaterialTheme.typography.bodyLarge
            .copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.background
            ),
        modifier = modifier
    )

    descr()
}