package org.gulnazidr.stepik.core.model

data class CourseDetail(
    val courseBaseInfo: Course,
    val workloadTime: String,
    val targetAudience: String,
    val requirements: String,
    val difficultyLevel: String,
    val acquiredSkills: List<String>,
    val acquiredAssets: List<String>,
    val learningFormat: String,
    val lessonsCount: Int,
)
