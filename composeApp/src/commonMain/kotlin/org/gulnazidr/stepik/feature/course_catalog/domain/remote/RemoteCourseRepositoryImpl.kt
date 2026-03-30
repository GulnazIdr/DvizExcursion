package org.gulnazidr.stepik.feature.course_catalog.domain.remote

import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed
import org.gulnazidr.stepik.core.network.ktor.course.KtorCourseRepository

class RemoteCourseRepositoryImpl(
    private val ktorCourseRepository: KtorCourseRepository
) : RemoteCourseRepository {
    override suspend fun getCourses(page: Int): Result<StepikCourse> {
        return ktorCourseRepository.getCourses(page)
    }

    override suspend fun getCourseById(id: Int): Result<StepikCourseDetailed> {
        return ktorCourseRepository.getCourseById(id)
    }

    override suspend fun getCoursesByIds(idList: List<Int>): Result<StepikCourseDetailed> {
        return ktorCourseRepository.getCoursesByIds(idList)
    }
}