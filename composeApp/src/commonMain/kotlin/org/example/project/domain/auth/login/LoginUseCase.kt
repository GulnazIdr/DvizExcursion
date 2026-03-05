package org.example.project.domain.auth.login

import org.example.project.domain.auth.LocalError

class LoginUseCase {
    operator fun invoke(
        name: String?,
        password: String?
    ): LocalError.FieldError?{
        return if(name.isNullOrBlank() || password.isNullOrBlank()) LocalError.FieldError.EMPTY_FIELD
            else null
    }
}