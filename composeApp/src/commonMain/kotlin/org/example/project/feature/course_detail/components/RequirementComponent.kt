package org.example.project.feature.course_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.course_detail_requirements_text
import stepik.composeapp.generated.resources.requirement

@Composable
fun RequirementComponent(
    requirements: String,
    textColor: Color
){
    if (requirements.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionBlock(
            title = Res.string.course_detail_requirements_text,
            textColor = textColor
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            DescriptionItem(
                resource = Res.drawable.requirement,
                text = requirements
                    .replace(Regex("<.*?>"), "")
                    .replace(Regex("-"), ""),
                textColor = textColor
            )
        }
    }
}