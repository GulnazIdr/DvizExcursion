package org.example.project.presentation.main.post

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.arrow_up

@Composable
fun FavoriteReaction(
    favoriteAmount: Int,
    onLike: () -> Unit,
    onDislike: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        PostIcon(
            iconResource = Res.drawable.arrow_up,
            modifier = Modifier.clickable(onClick = onLike)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = favoriteAmount.toString(),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        )

        Spacer(modifier = Modifier.width(10.dp))

        PostIcon(
            iconResource = Res.drawable.arrow_up,
            modifier = Modifier
                .rotate(180f)
                .clickable(onClick = onDislike)

        )
    }
}