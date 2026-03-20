package org.example.project.feature.auth.data

import org.example.project.core.database.impl.LocalUserRepositoryImpl
import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.RemoteError
import org.example.project.core.model.User
import org.example.project.feature.auth.domain.registration.RegisterRepository

class RegisterRepositoryImpl (
    private val localUserRepository: LocalUserRepositoryImpl
):  RegisterRepository{

    override suspend fun signup(user: User): AuthResult<Result<Boolean>, RemoteError> {
        localUserRepository.saveUser(user)
        return AuthResult.Success(Result.success(true))
    }
}