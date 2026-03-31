package org.gulnazidr.stepik.feature.auth.domain.login

import org.gulnazidr.stepik.feature.auth.domain.result.AuthError
import org.gulnazidr.stepik.feature.auth.domain.result.AuthResult

class LoginUseCase(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(userName: String, password: String)
    : AuthResult<Result<Boolean>, AuthError> {
        return loginRepository.login(userName, password)
    }
}