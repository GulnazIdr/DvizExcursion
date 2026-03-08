package org.example.project.feature.auth.presentation.mappers

import dvizexcursion.composeapp.generated.resources.Res
import dvizexcursion.composeapp.generated.resources.empty_field
import dvizexcursion.composeapp.generated.resources.no_digits
import dvizexcursion.composeapp.generated.resources.no_internet_field
import dvizexcursion.composeapp.generated.resources.no_letters
import dvizexcursion.composeapp.generated.resources.no_symbols
import dvizexcursion.composeapp.generated.resources.no_uppers
import dvizexcursion.composeapp.generated.resources.not_enough_characters
import dvizexcursion.composeapp.generated.resources.policy_unchecked_field
import dvizexcursion.composeapp.generated.resources.wrong_creds_field
import dvizexcursion.composeapp.generated.resources.wrong_format_field
import org.example.project.core.designsystem.UiText
import org.example.project.core.designsystem.UiText.ResourceString
import org.example.project.feature.auth.domain.LocalError.FieldError
import org.example.project.feature.auth.domain.LocalError.PasswordError
import org.example.project.feature.auth.domain.RemoteError

fun PasswordError.asUiText(): UiText {
    return when(this){
        PasswordError.NOT_ENOUGH_CHARACTERS -> ResourceString(
            Res.string.not_enough_characters
        )
        PasswordError.NO_LETTERS -> ResourceString(
            Res.string.no_letters
        )
        PasswordError.NO_DIGITS -> ResourceString(
            Res.string.no_digits
        )
        PasswordError.NO_UPPERCASE -> ResourceString(
            Res.string.no_uppers
        )
        PasswordError.NO_SYMBOLS -> ResourceString(
            Res.string.no_symbols
        )
        PasswordError.EMPTY_FIELD -> ResourceString(
            Res.string.empty_field
        )
    }
}

fun FieldError.asUiText(): UiText {
    return when(this){
        FieldError.EMPTY_FIELD -> ResourceString(
            Res.string.empty_field
        )
        FieldError.WRONG_FORMAT -> ResourceString(
            Res.string.wrong_format_field
        )
        FieldError.POLICY_UNCHECKED -> ResourceString(
            Res.string.policy_unchecked_field
        )
    }
}

fun RemoteError.asUiText(): UiText {
    return when(this) {
        RemoteError.WRONG_CREDENTIALS -> ResourceString(
            Res.string.wrong_creds_field
        )

        RemoteError.NETWORK_ERROR -> ResourceString(
            Res.string.no_internet_field
        )
    }
}