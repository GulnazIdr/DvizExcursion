package org.example.project.feature.auth.data

import org.example.project.core.database.source.LocalUserRepository
import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.RemoteError
import org.example.project.feature.auth.domain.login.LoginRepository

class LoginRepositoryImpl(): LoginRepository {
    override suspend fun login(
        name: String,
        password: String
    ): AuthResult<Result<Boolean>, RemoteError> {
        return if (name == "gulnaz" && password == "123")
            AuthResult.Success(Result.success(true))
        else
            AuthResult.Error(RemoteError.WRONG_CREDENTIALS)
    }
}