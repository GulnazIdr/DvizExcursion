package org.example.project.data

import org.example.project.domain.login.LoginRepository

class LoginRepositoryImpl: LoginRepository {
    override suspend fun login(
        name: String,
        password: String
    ): Result<Boolean> =
        if (name == "gulnaz" && password == "123")
            Result.success(true)
        else
            Result.failure(Exception("wrong credentials"))
}