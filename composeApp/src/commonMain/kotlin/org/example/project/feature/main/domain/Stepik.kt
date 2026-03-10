package org.example.project.feature.main.domain

data class Stepik(
    val pageInfo: PageInfo,
    val courses: List<Course>
)

data class StepikDetailed(
    val pageInfo: PageInfo,
    val courses: List<CourseDetail>
)

data class PageInfo(
    val page: Int,
    val hasNext: Boolean,
    val hasPrevious: Boolean
)
