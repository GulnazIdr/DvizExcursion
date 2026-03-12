package org.example.project.core.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.arrow

@Composable
fun BasicTopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomIconButton(
            idDrawable = Res.drawable.arrow,
            modifier = Modifier
                .clickable(onClick = onBack),
            size = 44.dp,
            iconSize = 22.dp
        )

        content()

    }
}
