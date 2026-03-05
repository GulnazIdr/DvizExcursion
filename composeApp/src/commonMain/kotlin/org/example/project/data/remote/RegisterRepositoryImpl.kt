package org.example.project.data.remote

import org.example.project.domain.auth.AuthResult
import org.example.project.domain.auth.RemoteError
import org.example.project.domain.auth.User
import org.example.project.domain.auth.registration.RegisterRepository

class RegisterRepositoryImpl:  RegisterRepository{
    override suspend fun signup(user: User): AuthResult<RemoteError> {
        return AuthResult.Success(Result.success(true))
    }
}