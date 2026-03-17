package org.example.project.core.common.result

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified D : Any, D2, C> HttpResponse.codeMapper(
    transformData: (D) -> D2, getCache: () -> C
): FetchResult<D2, NetworkError, C> {
    return when (this.status.value) {
        in 200..299 -> {
            val data = this.body<D>()
            val transformedData = transformData(data)
            FetchResult.Success(transformedData)
        }

        401 -> {
            FetchResult.Cache(getCache(), NetworkError.UNAUTHORIZED)
        }

        408 -> {
            FetchResult.Cache(getCache(), NetworkError.REQUEST_TIMEOUT)
        }

        409 -> {
            FetchResult.Cache(getCache(), NetworkError.CONFLICT)
        }

        413 -> {
            FetchResult.Cache(getCache(), NetworkError.PAYLOAD_TOO_LARGE)
        }

        in 500..599 -> {
            FetchResult.Cache(getCache(), NetworkError.SERVER_ERROR)
        }

        else -> {
            FetchResult.Cache(getCache(), NetworkError.UNKNOWN)
        }
    }
}
