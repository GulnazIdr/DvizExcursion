package org.example.project.core.common.result

sealed class FetchResult<out D, out E: Error>{
    data class Success<out D>(val successData: D): FetchResult<D, Nothing>()
    data class ErrorRes<out E: Error>(val error: E): FetchResult<Nothing, E>()
}