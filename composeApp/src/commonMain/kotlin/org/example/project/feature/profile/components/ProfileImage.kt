package org.example.project.feature.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.aakira.napier.Napier
import org.example.project.core.designsystem.components.CustomIconButton
import org.jetbrains.compose.resources.painterResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.close
import stepik.composeapp.generated.resources.edit
import stepik.composeapp.generated.resources.profile

@Composable
fun ProfileImage(
    imageUrl: String?,
    isEditEnabled: Boolean,
    onEdit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.size(120.dp)
    ) {
        if (imageUrl.isNullOrEmpty()) {
            Box(
                modifier = modifier.size(120.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.profile),
                    contentDescription = "User placeholder",
                    tint = Color.Gray,
                    modifier = Modifier.size(64.dp)
                )
            }
        } else {
            Box(
                modifier = modifier
                    .background(Color.LightGray)
                    .align(Alignment.Center),
            )
        }

        CustomIconButton(
            idDrawable = if (isEditEnabled) Res.drawable.close else Res.drawable.edit,
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            iconSize = 18.dp,
            modifier = Modifier
                .clickable(onClick = {
                    onEdit()
                })
                .align(
                    Alignment.BottomEnd
                )
        )
    }
}