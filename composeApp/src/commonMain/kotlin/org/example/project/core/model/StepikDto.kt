package org.example.project.core.model

import kotlinx.serialization.SerialName
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
    @SerialName("has_next")
    val hasNext: Boolean,
    @SerialName("has_previous")
    val hasPrevious: Boolean
)