package org.example.project.feature.auth.presentation.models

data class UserUi(
    val id: Int = 0,
    val name: String,
    val details: String = "",
    val shortBio: String = "",
    val profileImg: String = "",
    val email: String,
    val phone: String = "",
    val password: String = ""
)
