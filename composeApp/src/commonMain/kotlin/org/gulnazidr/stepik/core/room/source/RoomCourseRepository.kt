package org.gulnazidr.stepik.core.room.source

import org.gulnazidr.stepik.core.model.CourseDetail
import org.gulnazidr.stepik.core.model.StepikCourse
import org.gulnazidr.stepik.core.model.StepikCourseDetailed

interface RoomCourseRepository {
    suspend fun saveCourses(courseList: List<CourseDetail>): Boolean

    suspend fun getCourses(): Result<StepikCourse>

    suspend fun getCourseById(id: Int): Result<StepikCourseDetailed>

    suspend fun updateCourseDetailed(courseDetail: CourseDetail): Boolean

    suspend fun deleteCourse(): Boolean

    suspend fun getCoursesByIds(idList: List<Int>): Result<StepikCourseDetailed>
}