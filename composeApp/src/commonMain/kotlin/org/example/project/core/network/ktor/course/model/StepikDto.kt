package org.example.project.core.network.ktor.course.model

import kotlinx.serialization.Serializable
import org.example.project.core.network.ktor.models.MetaDto

@Serializable
data class StepikCourseDto(
    val meta: MetaDto,
    val courses: List<CourseDto>
)

@Serializable
data class StepikCourseDetailedDto(
    val meta: MetaDto,
    val courses: List<CourseDetailDto>
)