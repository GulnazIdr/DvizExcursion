package org.gulnazidr.stepik.core.domain.courses.source

import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed
import org.gulnazidr.stepik.core.network.ktor.models.DataWrapping

interface CourseRepository {
    suspend fun getCourses(page: Int): Result<DataWrapping<StepikCourse>>

    suspend fun getCourseById(id: Int): Result<DataWrapping<StepikCourseDetailed>>

    suspend fun getCoursesByIds(idList: List<Int>)
            : Result<DataWrapping<StepikCourseDetailed>>

  //  suspend fun refreshCourses(page: Int): Result<DataWrapping<StepikCourse>>
}