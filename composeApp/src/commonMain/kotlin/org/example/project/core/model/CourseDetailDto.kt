package org.example.project.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDetailDto(
    val id: Int,
    val title: String,
    val cover: String? = null,
    val summary: String,
    val price: String? = null,
    @SerialName("certificate_link")
    val certificateLink: String?,
    val workload: String,
    @SerialName("target_audience")
    val targetAudience: String,
    val requirements: String,
    val difficulty: String?,
    @SerialName("acquired_skills")
    val acquiredSkills: List<String>,
    @SerialName("acquired_assets")
    val acquiredAssets: List<String>,
    @SerialName("learning_format")
    val learningFormat: String,
    @SerialName("lessons_count")
    val lessonsCount: Int,
    @SerialName("learners_count")
    val learnersCount: Int
)
