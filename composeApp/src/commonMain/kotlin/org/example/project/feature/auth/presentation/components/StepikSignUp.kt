package org.example.project.feature.auth.presentation.components

import androidx.compose.runtime.Composable

@Composable
expect fun StepikSignUp(signup: () -> Unit = {}, navigateToMain: () -> Unit)