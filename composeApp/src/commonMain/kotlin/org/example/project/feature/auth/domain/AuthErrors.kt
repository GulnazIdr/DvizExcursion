package org.example.project.feature.auth.domain

sealed interface AuthError

sealed interface LocalError: AuthError{
    enum class PasswordError: LocalError{
        NOT_ENOUGH_CHARACTERS,
        NO_LETTERS,
        NO_DIGITS,
        NO_UPPERCASE,
        NO_SYMBOLS,
        EMPTY_FIELD,
    }

    enum class FieldError: LocalError {
        EMPTY_FIELD,
        WRONG_FORMAT,
        POLICY_UNCHECKED
    }
}

enum class RemoteError: AuthError{
    WRONG_CREDENTIALS
}