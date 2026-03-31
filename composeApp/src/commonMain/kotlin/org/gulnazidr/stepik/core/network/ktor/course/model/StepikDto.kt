package org.gulnazidr.stepik.core.network.ktor.course.model

import kotlinx.serialization.Serializable
import org.gulnazidr.stepik.core.network.ktor.models.MetaDto

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