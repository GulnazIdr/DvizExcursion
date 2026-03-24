package org.example.project.feature.course_detail.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.course_detail_skill_acquire_text
import stepik.composeapp.generated.resources.skill

@Composable
fun AquiredSkillsComponent(
    acquiredSkills: List<String>,
    textColor: Color
){
    if (acquiredSkills.isNotEmpty()) {
        Spacer(modifier = Modifier.height(16.dp))
        DescriptionBlock(
            title = Res.string.course_detail_skill_acquire_text,
            textColor = textColor
        ) {
            Spacer(modifier = Modifier.height(6.dp))
            acquiredSkills.forEach { skill ->
                Row(verticalAlignment = Alignment.Top) {
                    DescriptionItem(
                        resource = Res.drawable.skill,
                        text = skill.replace(
                            Regex("—"), ""
                        ),
                        textColor = textColor
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}