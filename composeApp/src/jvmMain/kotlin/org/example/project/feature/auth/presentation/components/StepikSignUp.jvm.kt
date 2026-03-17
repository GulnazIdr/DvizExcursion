package org.example.project.feature.auth.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.example.project.feature.auth.domain.DesktopViewmodel
import org.koin.compose.viewmodel.koinViewModel

@Composable
actual fun StepikSignUp(signup: () -> Unit) {
    val desktopViewmodel: DesktopViewmodel = koinViewModel()
    Button(onClick = {
        desktopViewmodel.auth()
    }){
        Text(text = "login stepik")
    }
}