package org.gulnazidr.stepik.feature.auth.domain.login

import org.gulnazidr.stepik.feature.auth.domain.result.LocalError

class LoginErrorUseCase {
    operator fun invoke(
        name: String?,
        password: String?
    ): LocalError.FieldError? {
        return if (name.isNullOrBlank() || password.isNullOrBlank()) LocalError.FieldError.EMPTY_FIELD
        else null
    }
}