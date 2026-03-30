package org.gulnazidr.stepik.feature.profile.presentation.components

import androidx.compose.foundation.background
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
import org.jetbrains.compose.resources.painterResource
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.profile

@Composable
fun ProfileImagePlaceHolder(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier.size(120.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Res.drawable.profile),
            contentDescription = "User placeholder",
            tint = Color.Gray,
            modifier = Modifier.size(64.dp)
        )
    }
}