package org.example.project.core.network

import io.github.aakira.napier.Napier
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import org.example.project.core.network.ktor.models.KtorDataWrapping

suspend inline fun <T, reified DTO> executeApiRequest(
    apiCall: suspend () -> HttpResponse,
    refreshToken: () -> Boolean,
    parseResponse: (DTO) -> T,
    onSuccessAction: suspend (T) -> Unit,
    getFromCache: suspend () -> Result<T>,
    validateResult: (T) -> Boolean = { true },
    validationError: () -> Throwable = { NothingFoundException("Resource not found") }
): Result<KtorDataWrapping<T>> {

    return runCatching {
        apiCall()
    }.map { response ->
        when (response.status.value) {
            401 -> {
                val isRefreshed = refreshToken()
                if (!isRefreshed) throw TokenRefreshException("failed to refresh token")
            }
            in 500..511 -> throw CustomServerException("server error $response")
        }

        KtorDataWrapping(
            data = parseResponse(response.body<DTO>()),
            isFromCache = false
        )
    }.fold(
        onSuccess = { ktorWrapping ->
            val data = ktorWrapping.data
            if (validateResult(data)) {
                onSuccessAction(data)
                Result.success(ktorWrapping)
            } else {
                throw validationError()
            }
        },
        onFailure = { error ->
            if (error is TokenRefreshException){
                Result.failure<T>(error)
            }
            getFromCache().fold(
                onSuccess = { res ->
                    Result.success(
                        KtorDataWrapping(
                            data = res,
                            isFromCache = true,
                            error = error
                        )
                    )
                },
                onFailure = { throwable ->
                    Napier.e("fetching course cache error $throwable")
                    Napier.e("fetching course remote error $error")
                    Result.failure(error)
                }
            )
        }
    )
}