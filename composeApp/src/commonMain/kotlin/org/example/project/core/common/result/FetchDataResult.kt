package org.example.project.core.common.result

sealed class FetchDataResult<out D, out E>(){
    data class Success<out D>(val data: D): FetchDataResult<D, Nothing>()
    data class Error<out E>(val error: E): FetchDataResult<Nothing, E>()
    data class Cache<out D, out E>(val cacheData: D, val error: E) : FetchDataResult<D, E>()
}