package org.example.project.feature.main.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class StepikDto(
    val meta: MetaDto,
    val courses: List<CourseDto>
)

@Serializable
data class StepikCourseDetailedDto(
    val meta: MetaDto,
    val courses: List<CourseDetailDto>
)

@Serializable
data class MetaDto(
    val page: Int,
    val has_next: Boolean,
    val has_previous: Boolean
)