package org.example.project.data.remote

import org.example.project.domain.auth.AuthResult
import org.example.project.domain.auth.RemoteError
import org.example.project.domain.auth.login.LoginRepository

class LoginRepositoryImpl: LoginRepository {
    override suspend fun login(
        name: String,
        password: String
    ): AuthResult<RemoteError> =
        if (name == "gulnaz" && password == "123")
            AuthResult.Success(Result.success(true))
        else
            AuthResult.Error(RemoteError.WRONG_CREDENTIALS)

//    override suspend fun login(
//        name: String,
//        password: String
//    ): Result<Boolean> =
//        if (name == "gulnaz" && password == "123")
//            Result.success(true)
//        else
//            Result.failure(Exception("wrong credentials"))
}