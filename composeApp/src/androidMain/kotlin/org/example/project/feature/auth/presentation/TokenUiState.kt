package org.example.project.feature.auth.presentation

data class TokenUiState(
    val input: String,
    val errorMessage: String,
    val isSaved: Boolean,
    val isLoading: Boolean
)
