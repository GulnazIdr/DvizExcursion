package org.gulnazidr.stepik.feature.course_detail.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.checkmark
import stepik.composeapp.generated.resources.course_detail_what_get_text

@Composable
fun AquiredAssetsComponent(
    acquiredAssets: List<String>,
    textColor: Color
){
    if (acquiredAssets.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))

        DescriptionBlock(
            title = Res.string.course_detail_what_get_text,
            textColor = textColor
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            acquiredAssets.forEach { asset ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    DescriptionItem(
                        resource = Res.drawable.checkmark,
                        text = asset,
                        textColor = textColor
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}