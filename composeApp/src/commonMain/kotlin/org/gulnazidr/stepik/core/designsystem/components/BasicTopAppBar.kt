package org.gulnazidr.stepik.core.designsystem.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.arrow
import org.jetbrains.compose.resources.painterResource

@Composable
fun BasicTopAppBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    isFirstInBackStack: Boolean = false,
    content: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .clickable(onClick = onBack)
        ) {
            if (!isFirstInBackStack) {
                Icon(
                    painter = painterResource(Res.drawable.arrow),
                    contentDescription = "icon",
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }

        content()

    }
}
