package org.example.project.core.common.result

sealed class FetchCourseResult<out D, out E>(){
    data class Success<out D>(val stepikData: D): FetchCourseResult<D, Nothing>()
    data class Error<out E>(val error: E): FetchCourseResult<Nothing, E>()
    data class Cache<out D, out E>(val cacheData: D, val error: E) : FetchCourseResult<D, E>()
}