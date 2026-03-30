package org.gulnazidr.stepik.feature.auth.domain.registration

import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.feature.auth.domain.result.AuthError
import org.gulnazidr.stepik.feature.auth.domain.result.AuthResult
import org.gulnazidr.stepik.feature.auth.domain.result.RemoteError

class RegisterUseCase(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(user: User): AuthResult<Result<Boolean>, AuthError> {
        return registerRepository.signup(user)
    }
}