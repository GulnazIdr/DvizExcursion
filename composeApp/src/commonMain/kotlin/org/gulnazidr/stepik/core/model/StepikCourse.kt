package org.gulnazidr.stepik.core.model

data class StepikCourse(
    val pageInfo: PageInfo,
    val courses: List<Course>
)

data class StepikCourseDetailed(
    val pageInfo: PageInfo,
    val courses: List<CourseDetail>
)