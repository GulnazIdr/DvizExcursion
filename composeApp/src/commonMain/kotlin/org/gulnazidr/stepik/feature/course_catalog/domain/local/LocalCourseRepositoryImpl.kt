package org.gulnazidr.stepik.feature.course_catalog.domain.local

import org.gulnazidr.stepik.core.model.CourseDetail
import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed
import org.gulnazidr.stepik.core.room.source.RoomCourseRepository

class LocalCourseRepositoryImpl (
    private val roomCourseRepository: RoomCourseRepository
): LocalCourseRepository{
    override suspend fun saveCourses(courseList: List<CourseDetail>): Boolean{
        return roomCourseRepository.saveCourses(courseList)
    }

    override suspend fun getCourses(): Result<StepikCourse>{
        return roomCourseRepository.getCourses()
    }

    override suspend fun getCourseById(id: Int): Result<StepikCourseDetailed> {
        return roomCourseRepository.getCourseById(id)
    }

    override suspend fun updateCourseDetailed(courseDetail: CourseDetail): Boolean{
        return roomCourseRepository.updateCourseDetailed(courseDetail)
    }

    override suspend fun deleteCourse(): Boolean{
        return roomCourseRepository.deleteCourse()
    }

    override suspend fun getCoursesByIds(idList: List<Int>): Result<StepikCourseDetailed> {
        return roomCourseRepository.getCoursesByIds(idList)
    }
}
