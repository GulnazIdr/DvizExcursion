package org.example.project.feature.course_catalog.course

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import org.example.project.core.designsystem.components.StepikCover

@Composable
fun CourseImage(
    image: String,
    modifier: Modifier = Modifier
){
    if (image.isNotEmpty())
        SubcomposeAsyncImage(
            model = image,
            contentDescription = "course image",
            loading = { StepikCover(modifier = modifier) },
            error = { StepikCover(modifier = modifier) },
            modifier = modifier
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
    else
        StepikCover(modifier = modifier)
}