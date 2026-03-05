package org.example.project.domain.auth.registration

import org.example.project.domain.auth.AuthResult
import org.example.project.domain.auth.RemoteError
import org.example.project.domain.auth.User

interface RegisterRepository {
    suspend fun signup(
        user: User
    ): AuthResult<RemoteError>
}