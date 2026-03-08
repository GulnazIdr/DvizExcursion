package org.example.project.feature.main.presentation.models

data class PostUi(
    val id: Int,
    val text: String,
    val image: String,
    val commentAmount: Int,
    val favoriteAmount: Int
)