package org.example.project.feature.auth.presentation.models

import org.example.project.core.designsystem.ui_logic.UiText

data class RegistrationUiState(
    val userName: String,
    val password: String,
    val email: String,
    val isPolicyChecked: Boolean,
    val isLoginButtonActive: Boolean,
    val nameError: UiText?,
    val pswdError: UiText?,
    val emailError: UiText?,
    val registerError: UiText?,
    val policyError: UiText?
)
