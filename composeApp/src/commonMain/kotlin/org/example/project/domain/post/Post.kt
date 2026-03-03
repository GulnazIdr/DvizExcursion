package org.example.project.domain.post

data class Post(
    val id: Int,
    val text: String,
    val image: String = "",
    val commentAmount: Int,
    val favoriteAmount: Int
)
