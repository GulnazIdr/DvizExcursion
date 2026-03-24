package org.example.project.core.network.ktor.model

import org.example.project.core.common.result.NetworkError

data class KtorDataWrapping<T>(
    val data: T,
    val isFromCache: Boolean,
    val error: Throwable? = null
)
