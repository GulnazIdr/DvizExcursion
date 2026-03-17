package org.example.project.core.network.ktor.source

import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.Stepik
import org.example.project.core.model.StepikDetailed

interface RemoteCourseRepository {
    suspend fun getCourses(page: Int): FetchResult<Stepik, NetworkError, List<CourseDetail>>

    suspend fun getCourseById(id: Int): FetchResult<StepikDetailed, NetworkError, CourseDetail?>
}