package org.example.project.feature.auth.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.example.project.core.designsystem.components.InputField
import org.example.project.core.designsystem.components.NavigationButton
import org.example.project.core.designsystem.components.StepikLogo
import org.example.project.feature.auth.presentation.AuthViewModel
import org.gulnazidr.dviz_excursion.R
import org.koin.compose.viewmodel.koinViewModel

@Composable
actual fun StepikSignUp(
    signup: () -> Unit,
){
    val viewModel: AuthViewModel = koinViewModel<AuthViewModel>()
    var input by remember { mutableStateOf("") }

    Column {
        StepikLogo(
            size = 40.dp,
            modifier = Modifier.clickable(onClick = { viewModel.openLoginPage() })
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.login_paste_url_text),
            style =  MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
        )


        InputField(
            value = input,
            onValueChange = { input = it }
        )

        Spacer(modifier = Modifier.height(5.dp))

        NavigationButton(
            onBtnClick = { viewModel.handle(input) },
            text = "login2"
        )
    }
}