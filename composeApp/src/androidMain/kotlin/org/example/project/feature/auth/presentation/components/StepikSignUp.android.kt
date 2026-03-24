package org.example.project.feature.auth.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
actual fun StepikSignUp(
    signup: () -> Unit,
    navigateToMain: () -> Unit
) {
    var isStepikLoginInfoOpen by rememberSaveable { mutableStateOf(false) }

    Column {
        ButtonStepikLogin(
            onClick = {
                isStepikLoginInfoOpen = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))

    }

    if (isStepikLoginInfoOpen) {
        DialogStepikLoginInfo(
            navigateToMain = navigateToMain,
            isCanceled = true,
            onCancelled = { isStepikLoginInfoOpen = it }
        )
    }
}