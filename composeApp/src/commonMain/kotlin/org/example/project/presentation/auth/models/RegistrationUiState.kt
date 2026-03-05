package org.example.project.presentation.auth.models

import org.example.project.presentation.common.UiText

data class RegistrationUiState(
    val userName: String,
    val password: String,
    val email: String,
    val isLoginButtonActive: Boolean,
    val nameError: UiText?,
    val pswdError: UiText?,
    val emailError: UiText?,
    val registerError: UiText?
)
