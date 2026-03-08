package org.example.project.feature.main.presentation.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.forward
import dvizexcursion.composeapp.generated.resources.share_text
import org.jetbrains.compose.resources.stringResource

@Composable
fun ForwardReaction(
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        PostIcon(
            iconResource = Res.drawable.forward
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = stringResource(Res.string.share_text),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )
    }
}