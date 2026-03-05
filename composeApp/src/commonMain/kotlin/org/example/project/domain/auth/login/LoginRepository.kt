package org.example.project.domain.auth.login

import org.example.project.domain.auth.AuthResult
import org.example.project.domain.auth.RemoteError

interface LoginRepository {
    suspend fun login(name: String, password: String): AuthResult<RemoteError>
}