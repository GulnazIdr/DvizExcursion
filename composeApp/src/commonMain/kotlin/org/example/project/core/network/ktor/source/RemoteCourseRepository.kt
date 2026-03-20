package org.example.project.core.network.ktor.source

import org.example.project.core.model.Stepik
import org.example.project.core.model.StepikDetailed
import org.example.project.core.network.ktor.model.KtorDataWrapping

interface RemoteCourseRepository {
    suspend fun getCourses(page: Int): Result<KtorDataWrapping<Stepik>>

    suspend fun getCourseById(id: Int): Result<KtorDataWrapping<StepikDetailed>>
}