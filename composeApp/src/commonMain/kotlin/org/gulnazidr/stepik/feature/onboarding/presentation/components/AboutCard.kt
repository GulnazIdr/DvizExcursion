package org.gulnazidr.stepik.feature.onboarding.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.gulnazidr.stepik.core.designsystem.components.getWindowWidth

@Composable
fun AboutCard(
    title: String,
    descr: String,
    color: Color,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedCornerShape(10.dp)
            )
            .width((getWindowWidth() * 0.7).dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = descr,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                textAlign = TextAlign.Start
            )
        }
    }
}
