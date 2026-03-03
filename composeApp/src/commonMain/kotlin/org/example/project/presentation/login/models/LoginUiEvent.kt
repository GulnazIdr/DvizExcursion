package org.example.project.presentation.login.models

sealed class LoginUiEvent {
    object LoginSuccessEvent: LoginUiEvent()
}