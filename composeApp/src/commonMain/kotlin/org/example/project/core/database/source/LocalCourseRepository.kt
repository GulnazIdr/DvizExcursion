package org.example.project.core.database.source

import org.example.project.core.model.CourseDetail
import org.example.project.core.model.StepikCourse
import org.example.project.core.model.StepikCourseDetailed

interface LocalCourseRepository {
    suspend fun saveCourses(courseList: List<CourseDetail>): Boolean

    suspend fun getCourses(): Result<StepikCourse>

    suspend fun getCourseById(id: Int): Result<StepikCourseDetailed?>

    suspend fun updateCourseDetailed(courseDetail: CourseDetail): Boolean

    suspend fun deleteCourse(): Boolean
}