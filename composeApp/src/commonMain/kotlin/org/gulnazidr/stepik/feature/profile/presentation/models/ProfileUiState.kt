package org.gulnazidr.stepik.feature.profile.presentation.models

import org.gulnazidr.stepik.core.designsystem.ui_logic.UiText

data class ProfileUiState(
    val userName: String,
    val email: String,
    val phone: String,
    val bio: String,
    val details: String,
    val isSaveButtonActive: Boolean,
    val nameError: UiText?,
    val emailError: UiText?,
    val phoneError: UiText?
)