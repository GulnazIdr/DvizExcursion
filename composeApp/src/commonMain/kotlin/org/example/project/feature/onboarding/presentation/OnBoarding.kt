package org.example.project.feature.onboarding.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.on_boarding1_descr
import stepik.composeapp.generated.resources.on_boarding1_title
import org.example.project.feature.onboarding.presentation.components.CircleOfIcons
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnBoarding(
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            CircleOfIcons()

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = stringResource(Res.string.on_boarding1_title),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = stringResource(Res.string.on_boarding1_descr),
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSecondary
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@Preview
@Composable
private fun OnBoardingPrev() {
    OnBoarding(
    )
}