package org.example.project.feature.auth.presentation.models

sealed class AuthUiEvent {
    object AuthSuccessEvent: AuthUiEvent()
}