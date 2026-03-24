package org.example.project.feature.auth.presentation.models

data class UserUi(
    val name: String,
    val password: String,
    val email: String,
    val phone: String = ""
)
