package org.example.project.feature.course_catalog.presentation.models

data class CourseUi(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val price: Double,
    val commentAmount: Int,
    val favoriteAmount: Int,
    val learnersCount: Int
)