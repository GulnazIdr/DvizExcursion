package org.gulnazidr.stepik.core.common.result

import io.github.aakira.napier.Napier
import org.gulnazidr.stepik.core.network.NothingFoundException
import org.gulnazidr.stepik.core.network.ktor.models.DataWrapping

suspend fun <T> Result<T>.executeApiRequest(
    onSuccessAction: suspend (T) -> Unit = {},
    getFromCache: suspend () -> Result<T>,
    validateResult: (T) -> Boolean = { true },
    validationError: () -> Throwable = { NothingFoundException("Resource not found") },
): Result<DataWrapping<T>> {

    return this.fold(
        onSuccess = { data ->
            if (validateResult(data)) {
                onSuccessAction(data)
                Result.success(
                    DataWrapping(data = data)
                )
            } else {
                throw validationError()
            }
        },
        onFailure = { error ->
            Napier.wtf("called1 $error")
            getFromCache().fold(
                onSuccess = { res ->
                    Result.success(
                        DataWrapping(
                            data = res,
                            isFromCache = true,
                            error = error
                        )
                    )
                },
                onFailure = { throwable ->
                    Napier.e("fetching from cache error $throwable")
                    Napier.e("fetching remote error $error")
                    Result.failure(error)
                }
            )
        }
    )
}