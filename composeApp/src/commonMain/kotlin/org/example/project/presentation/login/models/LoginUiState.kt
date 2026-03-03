package org.example.project

data class LoginUiState(
    val userName: String,
    val password: String,
    val isLoginButtonActive: Boolean,
    val error: String
)
