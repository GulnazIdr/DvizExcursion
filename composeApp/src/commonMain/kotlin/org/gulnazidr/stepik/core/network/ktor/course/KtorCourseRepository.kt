package org.gulnazidr.stepik.core.network.ktor.course

import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed

interface KtorCourseRepository {
    suspend fun getCourses(page: Int): Result<StepikCourse>

    suspend fun getCourseById(id: Int): Result<StepikCourseDetailed>

    suspend fun getCoursesByIds(idList: List<Int>): Result<StepikCourseDetailed>
}