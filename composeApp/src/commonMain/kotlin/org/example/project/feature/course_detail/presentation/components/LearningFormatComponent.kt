package org.example.project.feature.course_detail.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.course_detail_learning_format_text

@Composable
fun LearningFormatComponent(
    learningFormat: String,
    textColor: Color
){
    if (learningFormat.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionBlock(
            title = Res.string.course_detail_learning_format_text,
            textColor = textColor
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = learningFormat.replace(
                    Regex("<.*?>"),
                    ""
                ),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = textColor
                ),
            )
        }
    }
}