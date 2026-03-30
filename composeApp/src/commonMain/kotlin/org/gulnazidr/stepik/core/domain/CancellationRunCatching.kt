package org.gulnazidr.stepik.core.domain

import io.github.aakira.napier.Napier
import io.ktor.utils.io.CancellationException

inline fun <T, R> T.cancellationRunCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Napier.e("throwable exception $e")
        Result.failure(e)
    } catch (e: CancellationException){
        Napier.e("job was cancelled $e")
        Result.failure(e)
    }
}