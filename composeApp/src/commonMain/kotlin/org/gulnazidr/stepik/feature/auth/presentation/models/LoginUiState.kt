package org.gulnazidr.stepik.feature.auth.presentation.models

import org.gulnazidr.stepik.core.designsystem.ui_logic.UiText

data class LoginUiState(
    val userName: String,
    val password: String,
    val isLoginButtonActive: Boolean,
    val error: UiText,
)
