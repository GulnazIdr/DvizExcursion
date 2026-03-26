package org.example.project.core.common.result

import org.example.project.core.database.CustomRoomException
import org.example.project.core.network.CustomServerException
import org.example.project.core.network.TokenRefreshException
import java.net.UnknownHostException

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION,
    TOKEN_REFRESH,
    UNKNOWN
}

fun parseExceptionToNetworkError(exception: Throwable?): NetworkError?{
    return when (exception) {
        is CustomRoomException -> {
            NetworkError.SERVER_ERROR
        }

        is CustomServerException -> {
            NetworkError.SERVER_ERROR
        }

        is UnknownHostException -> {
            NetworkError.NO_INTERNET
        }

        is TokenRefreshException -> {
            NetworkError.TOKEN_REFRESH
        }

        null ->{
            null
        }

        else -> {
            NetworkError.UNKNOWN
        }
    }
}



