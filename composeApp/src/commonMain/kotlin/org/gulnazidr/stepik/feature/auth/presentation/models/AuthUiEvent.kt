package org.gulnazidr.stepik.feature.auth.presentation.models

sealed class AuthUiEvent {
    object AuthSuccessEvent: AuthUiEvent()
}