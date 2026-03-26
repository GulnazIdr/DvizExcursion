package org.example.project.core.designsystem.ui_logic.mapper

import org.example.project.core.common.result.NetworkError
import org.example.project.core.designsystem.ui_logic.UiText
import org.example.project.core.designsystem.ui_logic.UiText.*
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.remote_error_no_internet_field
import stepik.composeapp.generated.resources.remote_error_request_timeout_field
import stepik.composeapp.generated.resources.remote_error_too_many_requests_field
import stepik.composeapp.generated.resources.remote_error_uknown_error_field

fun NetworkError.asUiText(): UiText {
    return when(this){
        NetworkError.REQUEST_TIMEOUT -> ResourceString(
            Res.string.remote_error_request_timeout_field
        )
        NetworkError.UNAUTHORIZED -> TODO()
        NetworkError.CONFLICT -> TODO()
        NetworkError.TOO_MANY_REQUESTS -> ResourceString(
            Res.string.remote_error_too_many_requests_field
        )
        NetworkError.NO_INTERNET -> ResourceString(
            Res.string.remote_error_no_internet_field
        )
        NetworkError.PAYLOAD_TOO_LARGE -> TODO()
        NetworkError.SERVER_ERROR -> ResourceString(
            Res.string.remote_error_uknown_error_field
        )
        NetworkError.SERIALIZATION -> TODO()
        NetworkError.UNKNOWN -> ResourceString(
            Res.string.remote_error_uknown_error_field
        )

        NetworkError.TOKEN_REFRESH -> TODO()
    }
}