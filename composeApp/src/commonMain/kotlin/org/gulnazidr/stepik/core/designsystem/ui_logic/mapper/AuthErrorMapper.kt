package org.gulnazidr.stepik.core.designsystem.ui_logic.mapper

import org.gulnazidr.stepik.core.designsystem.ui_logic.UiText
import org.gulnazidr.stepik.core.designsystem.ui_logic.UiText.ResourceString
import org.gulnazidr.stepik.feature.auth.domain.result.AuthError
import org.gulnazidr.stepik.feature.auth.domain.result.LocalError
import org.gulnazidr.stepik.feature.auth.domain.result.LocalError.FieldError
import org.gulnazidr.stepik.feature.auth.domain.result.LocalError.PasswordError
import org.gulnazidr.stepik.feature.auth.domain.result.RemoteError
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.auth_unknown_error
import stepik.composeapp.generated.resources.registration_error_empty_field
import stepik.composeapp.generated.resources.registration_error_no_digits
import stepik.composeapp.generated.resources.registration_error_no_letters
import stepik.composeapp.generated.resources.registration_error_no_symbols
import stepik.composeapp.generated.resources.registration_error_no_uppers
import stepik.composeapp.generated.resources.registration_error_not_enough_characters
import stepik.composeapp.generated.resources.registration_error_policy_unchecked_field
import stepik.composeapp.generated.resources.registration_error_wrong_creds_field
import stepik.composeapp.generated.resources.registration_error_wrong_format_field

fun AuthError.asUiText(): UiText{
    return when(this){
        LocalError.DataStoreError.SAVE_USER -> ResourceString(
            Res.string.auth_unknown_error
        )
        FieldError.EMPTY_FIELD -> ResourceString(
            Res.string.registration_error_empty_field
        )
        FieldError.WRONG_FORMAT -> ResourceString(
            Res.string.registration_error_wrong_format_field
        )
        FieldError.POLICY_UNCHECKED -> ResourceString(
            Res.string.registration_error_policy_unchecked_field
        )
        PasswordError.NOT_ENOUGH_CHARACTERS -> ResourceString(
            Res.string.registration_error_not_enough_characters
        )
        PasswordError.NO_LETTERS -> ResourceString(
            Res.string.registration_error_no_letters
        )
        PasswordError.NO_DIGITS -> ResourceString(
            Res.string.registration_error_no_digits
        )
        PasswordError.NO_UPPERCASE -> ResourceString(
            Res.string.registration_error_no_uppers
        )
        PasswordError.NO_SYMBOLS -> ResourceString(
            Res.string.registration_error_no_symbols
        )
        PasswordError.EMPTY_FIELD -> ResourceString(
            Res.string.registration_error_empty_field
        )
        RemoteError.WRONG_CREDENTIALS -> ResourceString(
            Res.string.registration_error_wrong_creds_field
        )
    }
}