package org.example.project.feature.main.presentation.course

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.post_image_alt
import org.example.project.core.designsystem.components.StepikCover
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
            loading = { StepikCover(modifier = modifier) },
            error = { StepikCover(modifier = modifier) },
            modifier = modifier
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
    else
        StepikCover(modifier = modifier)
}