package org.gulnazidr.stepik.feature.profile.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.gulnazidr.stepik.core.designsystem.components.CircleLoading
import org.gulnazidr.stepik.core.designsystem.components.CustomIconButton
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.close
import stepik.composeapp.generated.resources.edit

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
            ProfileImagePlaceHolder(
                modifier.align(Alignment.Center)
            )
        } else {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = "course image",
                loading = { CircleLoading() },
                error = {
                    ProfileImagePlaceHolder(
                        modifier.align(Alignment.Center)
                    )
                },
                modifier = modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
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
