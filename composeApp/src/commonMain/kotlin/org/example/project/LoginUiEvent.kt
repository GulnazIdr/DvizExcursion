package org.example.project

sealed class LoginUiEvent {
    object LoginSuccessEvent: LoginUiEvent()
}