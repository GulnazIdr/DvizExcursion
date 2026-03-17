package org.example.project.core.common.result

sealed class FetchResult<out D,out E: Error, out C > {
    data class Success<out D>(val successData: D): FetchResult<D, Nothing, Nothing>()
    data class Cache<out C, out E: Error>(
        val cacheData: C, val cacheError: E
    ) : FetchResult<Nothing, E, C>()
}