package org.example.project.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseDetailDto(
    val id: Int,
    val title: String,
    val cover: String? = null,
    val summary: String,
    val price: String? = null,
    val certificate_link: String?,

    val workload: String,
    val target_audience: String,
    val requirements: String,
    val difficulty: String?,
    val acquired_skills: List<String>,
    val acquired_assets: List<String>,
    val learning_format: String,
    val lessons_count: Int,
)
