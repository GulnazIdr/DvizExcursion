package org.gulnazidr.stepik.feature.onboarding.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import stepik.composeapp.generated.resources.Res
import org.gulnazidr.stepik.feature.onboarding.presentation.components.AboutBlock
import org.gulnazidr.stepik.core.designsystem.components.NavigationButton
import org.jetbrains.compose.resources.stringResource
import stepik.composeapp.generated.resources.on_boarding2_get_started_text

@Composable
fun OnBoarding2(
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        AboutBlock()

        Spacer(modifier = Modifier.weight(1f))

        NavigationButton(
            onBtnClick = navigateToLogin,
            text = stringResource(Res.string.on_boarding2_get_started_text),
           // modifier = Modifier.padding(bottom = 50.dp)
        )
    }
}

@Preview
@Composable
private fun OnBoardingPrev2() {
    OnBoarding2(
        navigateToLogin = {},
    )
}