package org.example.project.core.network.ktor.models

data class KtorDataWrapping<T>(
    val data: T,
    val isFromCache: Boolean,
    val error: Throwable? = null
)