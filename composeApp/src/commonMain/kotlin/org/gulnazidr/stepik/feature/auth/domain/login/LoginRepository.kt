package org.gulnazidr.stepik.feature.auth.domain.login

import org.gulnazidr.stepik.feature.auth.domain.result.AuthError
import org.gulnazidr.stepik.feature.auth.domain.result.AuthResult

interface LoginRepository {
    suspend fun login(name: String, password: String): AuthResult<Result<Boolean>, AuthError>
}