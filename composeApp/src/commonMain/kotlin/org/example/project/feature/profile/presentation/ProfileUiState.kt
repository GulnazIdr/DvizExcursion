package org.example.project.feature.profile.presentation

import org.example.project.core.designsystem.ui_logic.UiText

data class ProfileUiState(
    val userName: String,
    val email: String,
    val phone: String,
    val isSaveButtonActive: Boolean,
    val nameError: UiText?,
    val emailError: UiText?,
    val phoneError: UiText?,
)
