package org.gulnazidr.stepik.core.domain.courses.result

import org.gulnazidr.stepik.core.model.Course

data class CourseSuccessResult(
    val successData: List<Course>,
    val hasNext: Boolean
)