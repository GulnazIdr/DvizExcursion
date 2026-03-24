package org.example.project.core.model

data class User(
    val id: Int = 0,
    val name: String,
    val email: String,
    val password: String,
    val phone: String = ""
)