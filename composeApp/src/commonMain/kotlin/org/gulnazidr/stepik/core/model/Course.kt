package org.gulnazidr.stepik.core.model

data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val commentAmount: Int,
    val favoriteAmount: Int,
    val price: Double,
    val learnersCount: Int,
    val authorList: List<User> = emptyList()
)