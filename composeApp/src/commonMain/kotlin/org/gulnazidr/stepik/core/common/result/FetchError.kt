package org.gulnazidr.stepik.core.common.result

import org.gulnazidr.stepik.core.network.RequestTimeOutException
import org.gulnazidr.stepik.core.network.TokenRefreshException
import java.net.UnknownHostException

sealed interface FetchError: Error

enum class UserNetworkError: FetchError {
    REQUEST_TIMEOUT,
    NO_INTERNET,
    UNKNOWN
}
enum class ServerNetworkError: FetchError {
    TOKEN_REFRESH
}

fun parseExceptionToNetworkError(exception: Throwable?): FetchError?{
    return when (exception) {
        is RequestTimeOutException -> {
            UserNetworkError.REQUEST_TIMEOUT
        }

        is UnknownHostException -> {
            UserNetworkError.NO_INTERNET
        }

        is TokenRefreshException -> {
            ServerNetworkError.TOKEN_REFRESH
        }

        null ->{
            null
        }

        else -> {
            UserNetworkError.UNKNOWN
        }
    }
}



