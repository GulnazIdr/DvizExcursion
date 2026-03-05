package org.example.project.presentation.onboarding.components

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.post_descr_about
import dvizexcursion.composeapp.generated.resources.post_title_about
import org.example.project.presentation.components.getWindowWidth
import org.jetbrains.compose.resources.stringResource

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
            .width((getWindowWidth() * 0.6).dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = descr,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                ),
                textAlign = TextAlign.Start
            )
        }
    }
}

@Preview
@Composable
fun AboutCardPrev(){
    AboutCard(
        title = stringResource(Res.string.post_title_about),
        descr = stringResource(Res.string.post_descr_about),
        color = MaterialTheme.colorScheme.primary
    )
}