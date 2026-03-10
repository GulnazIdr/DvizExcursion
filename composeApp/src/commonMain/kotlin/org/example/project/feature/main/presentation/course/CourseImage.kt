package org.example.project.feature.main.presentation.course

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.post_image_alt
import org.example.project.core.designsystem.components.LoadingGrayBox
import org.jetbrains.compose.resources.stringResource

@Composable
fun CourseImage(
    image: String,
    modifier: Modifier = Modifier
){
    if (image.isNotEmpty())
        SubcomposeAsyncImage(
            model = image,
            contentDescription = stringResource(Res.string.post_image_alt),
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp)),
            loading = {
                LoadingGrayBox(
                    modifier = modifier
                )
            },
            error = {
                LoadingGrayBox(
                    modifier = modifier
                )
            }
        )
    else
        LoadingGrayBox(
            modifier = modifier
        )
}