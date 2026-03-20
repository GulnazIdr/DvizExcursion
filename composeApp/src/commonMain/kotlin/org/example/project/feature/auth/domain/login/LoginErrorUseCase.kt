package org.example.project.feature.auth.domain.login

import org.example.project.feature.auth.domain.LocalError

class LoginErrorUseCase {
    operator fun invoke(
        name: String?,
        password: String?
    ): LocalError.FieldError? {
        return if (name.isNullOrBlank() || password.isNullOrBlank()) LocalError.FieldError.EMPTY_FIELD
        else null
    }
}