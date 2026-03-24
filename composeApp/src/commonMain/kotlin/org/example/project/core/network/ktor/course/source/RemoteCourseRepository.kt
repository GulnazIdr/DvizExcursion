package org.example.project.core.network.ktor.course.source

import org.example.project.core.model.StepikCourse
import org.example.project.core.model.StepikCourseDetailed
import org.example.project.core.network.ktor.models.KtorDataWrapping

interface RemoteCourseRepository {
    suspend fun getCourses(page: Int): Result<KtorDataWrapping<StepikCourse>>

    suspend fun getCourseById(id: Int): Result<KtorDataWrapping<StepikCourseDetailed>>
}