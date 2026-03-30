package org.gulnazidr.stepik.core.network.ktor.models

data class DataWrapping<T>(
    val data: T,
    val isFromCache: Boolean = false,
    val error: Throwable? = null
)