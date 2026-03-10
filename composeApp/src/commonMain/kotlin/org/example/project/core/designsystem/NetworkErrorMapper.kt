package org.example.project.core.designsystem

import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.no_internet_field
import dvizexcursion.composeapp.generated.resources.request_timeout_field
import dvizexcursion.composeapp.generated.resources.too_many_requests_field
import dvizexcursion.composeapp.generated.resources.uknown_error_field
import org.example.project.core.common.result.NetworkError

fun NetworkError.asUiText(): UiText{
    return when(this){
        NetworkError.REQUEST_TIMEOUT -> UiText.ResourceString(
            Res.string.request_timeout_field
        )
        NetworkError.UNAUTHORIZED -> TODO()
        NetworkError.CONFLICT -> TODO()
        NetworkError.TOO_MANY_REQUESTS -> UiText.ResourceString(
            Res.string.too_many_requests_field
        )
        NetworkError.NO_INTERNET -> UiText.ResourceString(
            Res.string.no_internet_field
        )
        NetworkError.PAYLOAD_TOO_LARGE -> TODO()
        NetworkError.SERVER_ERROR -> UiText.ResourceString(
            Res.string.uknown_error_field
        )
        NetworkError.SERIALIZATION -> TODO()
        NetworkError.UNKNOWN -> UiText.ResourceString(
            Res.string.uknown_error_field
        )
    }
}