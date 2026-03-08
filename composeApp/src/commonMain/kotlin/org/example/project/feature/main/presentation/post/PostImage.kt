package org.example.project.feature.main.presentation.post

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.SubcomposeAsyncImage
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.post_image_alt
import org.example.project.core.designsystem.components.LoadingGrayBox
import org.jetbrains.compose.resources.stringResource

@Composable
fun PostImage(
    image: String,
){
    LoadingGrayBox{
        SubcomposeAsyncImage(
            model = image,
            contentDescription = stringResource(Res.string.post_image_alt),
            modifier = Modifier.fillMaxSize()
        )
    }
}