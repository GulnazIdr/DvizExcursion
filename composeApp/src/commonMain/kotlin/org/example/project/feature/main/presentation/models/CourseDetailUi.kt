package org.example.project.feature.main.presentation.models

data class CourseDetailUi(
    val courseUi: CourseUi,
    val workloadTime: String,
    val targetAudience: String,
    val requirements: String,
    val difficultyLevel: String,
    val acquiredSkills: List<String>,
    val acquiredAssets: List<String>,
    val learningFormat: String,
    val lessonsCount: Int,
)
