package org.example.project.feature.auth.data

import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.RemoteError
import org.example.project.feature.auth.domain.User
import org.example.project.feature.auth.domain.registration.RegisterRepository

class RegisterRepositoryImpl:  RegisterRepository{
    override suspend fun signup(user: User): AuthResult<RemoteError> {
        return AuthResult.Success(Result.success(true))
    }
}