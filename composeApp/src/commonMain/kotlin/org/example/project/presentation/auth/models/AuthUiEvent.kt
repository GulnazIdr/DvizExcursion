package org.example.project.presentation.auth.models

sealed class AuthUiEvent {
    object AuthSuccessEvent: AuthUiEvent()
}