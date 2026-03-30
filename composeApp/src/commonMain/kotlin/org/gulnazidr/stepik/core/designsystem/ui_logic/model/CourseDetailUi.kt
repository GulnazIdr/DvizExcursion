package org.gulnazidr.stepik.core.designsystem.ui_logic.model

data class CourseDetailUi(
    val courseUi: CourseUi,

    val workloadTime: String = "",
    val targetAudience: String = "",
    val requirements: String = "",
    val difficultyLevel: String = "",
    val acquiredSkills: List<String> = emptyList(),
    val acquiredAssets: List<String> = emptyList(),
    val learningFormat: String = "",
    val lessonsCount: Int = 0,
)
