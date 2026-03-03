package org.example.project.presentation.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.get_started_text
import org.example.project.presentation.components.common.NavigationButton
import org.example.project.presentation.onboarding.components.AboutBlock
import org.example.project.presentation.onboarding.components.RowNavigationCircle
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnBoarding2(
    paddingValues: PaddingValues,
    navigateToLogin: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        AboutBlock(
            modifier = Modifier.padding(paddingValues)
        )

        Spacer(modifier = Modifier.weight(1f))

        NavigationButton(
            onBtnClick = navigateToLogin,
            text = stringResource(Res.string.get_started_text),
            modifier = Modifier.padding(bottom = 50.dp)
        )
    }
}

@Preview
@Composable
private fun OnBoardingPrev2() {
    OnBoarding2(
        navigateToLogin = {},
        paddingValues = PaddingValues()
    )
}