package org.example.project.feature.auth.domain

sealed interface Error

sealed interface LocalError: Error{
    enum class PasswordError: Error{
        NOT_ENOUGH_CHARACTERS,
        NO_LETTERS,
        NO_DIGITS,
        NO_UPPERCASE,
        NO_SYMBOLS,
        EMPTY_FIELD,
    }

    enum class FieldError: Error {
        EMPTY_FIELD,
        WRONG_FORMAT,
        POLICY_UNCHECKED
    }
}

enum class RemoteError: Error{
    NETWORK_ERROR,
    WRONG_CREDENTIALS
}