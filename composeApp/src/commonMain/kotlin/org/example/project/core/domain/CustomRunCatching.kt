package org.example.project.core.domain

import io.github.aakira.napier.Napier
import java.net.UnknownHostException

inline fun <T, R> T.customRunCatching(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: Throwable) {
        Napier.e("throwable exception $e")
        Result.failure(e)
    } catch (e: Exception){
        Napier.e("exception $e")
        Result.failure(e)
    }
}