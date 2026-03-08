package org.example.project.feature.auth.presentation.models

import org.example.project.core.designsystem.UiText

data class LoginUiState(
    val userName: String,
    val password: String,
    val isLoginButtonActive: Boolean,
    val error: UiText,
)
