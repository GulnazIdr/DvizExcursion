package org.example.project.feature.auth.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.arrow
import dvizexcursion.composeapp.generated.resources.login_text
import dvizexcursion.composeapp.generated.resources.registration_text
import org.example.project.core.designsystem.components.CustomIconButton
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthTopAppBar(
    onBack: () -> Unit,
    isRegistationScreen: Boolean,
    onAuth: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        CustomIconButton(
            idDrawable = Res.drawable.arrow,
            modifier = Modifier
                .clickable(onClick = onBack)
                .align(Alignment.TopStart),
            size = 44.dp,
            iconSize = 22.dp
        )

        Text(
            text = stringResource(
                if (isRegistationScreen) Res.string.login_text
                else Res.string.registration_text
            ),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .clickable(onClick = onAuth)
        )
    }
}

@Preview
@Composable
private fun AuthTopAppBarPrev() {
    AuthTopAppBar(
        onBack = {},
        isRegistationScreen = true,
        onAuth = {}
    )
}