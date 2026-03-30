package org.gulnazidr.stepik.core.designsystem.ui_logic.mapper

import org.gulnazidr.stepik.core.common.result.UserNetworkError
import org.gulnazidr.stepik.core.designsystem.ui_logic.UiText
import org.gulnazidr.stepik.core.designsystem.ui_logic.UiText.ResourceString
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.remote_error_no_internet_field
import stepik.composeapp.generated.resources.remote_error_request_timeout_field
import stepik.composeapp.generated.resources.remote_error_uknown_error_field

fun UserNetworkError.asUiText(): UiText {
    return when(this){
        UserNetworkError.REQUEST_TIMEOUT -> ResourceString(
            Res.string.remote_error_request_timeout_field
        )
        UserNetworkError.NO_INTERNET ->  ResourceString(
            Res.string.remote_error_no_internet_field
        )
        UserNetworkError.UNKNOWN -> ResourceString(
            Res.string.remote_error_uknown_error_field
        )
    }
}