package org.gulnazidr.stepik.feature.auth.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.login_text
import stepik.composeapp.generated.resources.registration_text
import org.gulnazidr.stepik.core.designsystem.components.BasicTopAppBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthTopAppBar(
    onBack: () -> Unit,
    isRegistationScreen: Boolean,
    isFirstInBackStack: Boolean,
    onAuth: () -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTopAppBar(
        onBack = onBack,
        isFirstInBackStack = isFirstInBackStack,
        modifier = modifier
    ){
        Text(
            text = stringResource(
                if (isRegistationScreen) Res.string.login_text
                else Res.string.registration_text
            ),
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondary
            ),
            modifier = Modifier
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
        onAuth = {},
        isFirstInBackStack = false
    )
}