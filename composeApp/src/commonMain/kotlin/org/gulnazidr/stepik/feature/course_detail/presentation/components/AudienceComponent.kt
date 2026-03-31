package org.gulnazidr.stepik.feature.course_detail.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.course_detail_target_audience_text

@Composable
fun AudienceComponent(
    targetAudience: String,
    textColor: Color
){
    if (targetAudience.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionBlock(
            title = Res.string.course_detail_target_audience_text,
            textColor = textColor
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = targetAudience,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = textColor
                )
            )
        }
    }
}