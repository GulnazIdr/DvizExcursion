package org.example.project.feature.auth.domain.login

import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.RemoteError

interface LoginRepository {
    suspend fun login(name: String, password: String): AuthResult<Result<Boolean>, RemoteError>
}