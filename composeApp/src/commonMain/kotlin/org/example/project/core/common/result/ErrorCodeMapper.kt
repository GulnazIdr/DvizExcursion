package org.example.project.core.common.result

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified D : Any, F> HttpResponse?.codeMapper(transform: (D) -> F)
: FetchResult<F, NetworkError> {
    return when (this?.status?.value) {
        in 200..299 -> {
            if (this != null) {
                val data = this.body<D>()
                val transformedData = transform(data)
                FetchResult.Success(transformedData)
            } else {
                FetchResult.ErrorRes(NetworkError.UNKNOWN)
            }
        }
        401 -> FetchResult.ErrorRes(NetworkError.UNAUTHORIZED)
        408 -> FetchResult.ErrorRes(NetworkError.REQUEST_TIMEOUT)
        409 -> FetchResult.ErrorRes(NetworkError.CONFLICT)
        413 -> FetchResult.ErrorRes(NetworkError.PAYLOAD_TOO_LARGE)
        in 500..599 -> FetchResult.ErrorRes(NetworkError.SERVER_ERROR)
        else -> FetchResult.ErrorRes(NetworkError.UNKNOWN)
    }
}
