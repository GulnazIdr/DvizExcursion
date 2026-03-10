package org.example.project.feature.main.domain

import org.example.project.core.common.result.FetchResult
import org.example.project.core.common.result.NetworkError

interface StepikApi {
    suspend fun getCourses(page: Int): FetchResult<Stepik, NetworkError>

    suspend fun getCourseById(id: Int): FetchResult<StepikDetailed, NetworkError>
}