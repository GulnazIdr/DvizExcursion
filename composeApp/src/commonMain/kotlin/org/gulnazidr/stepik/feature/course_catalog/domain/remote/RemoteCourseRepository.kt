package org.gulnazidr.stepik.feature.course_catalog.domain.remote

import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed

interface RemoteCourseRepository {
    suspend fun getCourses(page: Int): Result<StepikCourse>

    suspend fun getCourseById(id: Int): Result<StepikCourseDetailed>

    suspend fun getCoursesByIds(idList: List<Int>)
            : Result<StepikCourseDetailed>
}