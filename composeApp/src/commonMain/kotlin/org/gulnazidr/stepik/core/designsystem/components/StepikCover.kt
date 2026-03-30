package org.gulnazidr.stepik.core.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.stepik_cover
import org.jetbrains.compose.resources.painterResource

@Composable
fun StepikCover(
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(Res.drawable.stepik_cover),
        contentDescription = "stepik cover image",
        contentScale = ContentScale.Crop,
        modifier = modifier.clip(RoundedCornerShape(10.dp))
    )
}