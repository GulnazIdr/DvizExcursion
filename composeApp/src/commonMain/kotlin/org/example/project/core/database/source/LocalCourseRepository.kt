package org.example.project.core.database.source

import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.Stepik
import org.example.project.core.model.StepikDetailed

    interface LocalCourseRepository {
    suspend fun saveCourses(courseList: List<CourseDetail>): Boolean

    suspend fun getCourses(): Result<Stepik>

    suspend fun getCourseById(id: Int): Result<StepikDetailed?>

    suspend fun updateCourseDetailed(courseDetail: CourseDetail): Boolean

    suspend fun deleteCourse(): Boolean
}