package org.example.project.core.common.result

import org.example.project.core.database.CustomRoomException
import org.example.project.core.network.ktor.CustomServerException
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

        null ->{
            null
        }

        else -> {
            NetworkError.UNKNOWN
        }
    }
}



