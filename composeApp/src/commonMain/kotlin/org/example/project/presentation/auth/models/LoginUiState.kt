package org.example.project.presentation.auth.models

import org.example.project.presentation.common.UiText

data class LoginUiState(
    val userName: String,
    val password: String,
    val isLoginButtonActive: Boolean,
    val error: UiText,
)
