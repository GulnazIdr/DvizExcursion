package org.example.project.feature.main.domain

data class Post(
    val id: Int,
    val text: String,
    val image: String = "",
    val commentAmount: Int,
    val favoriteAmount: Int
)
