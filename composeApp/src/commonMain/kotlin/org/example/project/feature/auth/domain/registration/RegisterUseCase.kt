package org.example.project.feature.auth.domain.registration

import org.example.project.core.model.User
import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.RemoteError
import org.example.project.core.datastore.source.DataStoreRepository

class RegisterUseCase(
    private val registerRepository: RegisterRepository
) {
    suspend operator fun invoke(user: User): AuthResult<Result<Boolean>, RemoteError> {
        return registerRepository.signup(user)
    }
}