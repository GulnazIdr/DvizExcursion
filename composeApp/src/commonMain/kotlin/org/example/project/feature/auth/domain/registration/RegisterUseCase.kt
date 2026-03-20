package org.example.project.feature.auth.domain.registration

import org.example.project.core.model.User
import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.RemoteError
import org.example.project.feature.onboarding.domain.DataStoreRepository

class RegisterUseCase(
    private val registerRepository: RegisterRepository,
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(user: User): AuthResult<Result<Boolean>, RemoteError> {
        val res = registerRepository.signup(user)

        if (res is AuthResult.Success) {
            dataStoreRepository.setLoggedIn()
        }

        return res
    }
}