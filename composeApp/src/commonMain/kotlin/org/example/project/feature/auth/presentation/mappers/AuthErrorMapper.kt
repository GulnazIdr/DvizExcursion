package org.example.project.feature.auth.presentation.mappers

import org.example.project.core.designsystem.ui_logic.UiText
import org.example.project.core.designsystem.ui_logic.UiText.ResourceString
import org.example.project.feature.auth.domain.LocalError.FieldError
import org.example.project.feature.auth.domain.LocalError.PasswordError
import org.example.project.feature.auth.domain.RemoteError
import stepik.composeapp.generated.resources.Res
import stepik.composeapp.generated.resources.registration_error_empty_field
import stepik.composeapp.generated.resources.registration_error_no_digits
import stepik.composeapp.generated.resources.registration_error_no_letters
import stepik.composeapp.generated.resources.registration_error_no_symbols
import stepik.composeapp.generated.resources.registration_error_no_uppers
import stepik.composeapp.generated.resources.registration_error_not_enough_characters
import stepik.composeapp.generated.resources.registration_error_policy_unchecked_field
import stepik.composeapp.generated.resources.registration_error_wrong_creds_field
import stepik.composeapp.generated.resources.registration_error_wrong_format_field

fun PasswordError.asUiText(): UiText {
    return when(this){
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
    }
}

fun FieldError.asUiText(): UiText {
    return when(this){
        FieldError.EMPTY_FIELD -> ResourceString(
            Res.string.registration_error_empty_field
        )
        FieldError.WRONG_FORMAT -> ResourceString(
            Res.string.registration_error_wrong_format_field
        )
        FieldError.POLICY_UNCHECKED -> ResourceString(
            Res.string.registration_error_policy_unchecked_field
        )
    }
}

fun RemoteError.asUiText(): UiText {
    return when(this) {
        RemoteError.WRONG_CREDENTIALS -> ResourceString(
            Res.string.registration_error_wrong_creds_field
        )
    }
}