package org.example.project.feature.course_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun DescriptionItem(
    resource: DrawableResource,
    text: String,
    textColor: Color
){
    Icon(
        painter = painterResource(resource),
        tint = MaterialTheme.colorScheme.background,
        contentDescription = "",
        modifier = Modifier.size(25.dp)
    )

    Spacer(modifier = Modifier.width(8.dp))
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = textColor
        )
    )
}