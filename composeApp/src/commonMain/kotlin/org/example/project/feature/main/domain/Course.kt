package org.example.project.feature.main.domain

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val commentAmount: Int,
    val favoriteAmount: Int,
    val price: Int
)