package org.example.project.feature.auth.domain.registration

import org.example.project.feature.auth.domain.AuthResult
import org.example.project.feature.auth.domain.RemoteError
import org.example.project.core.model.User

interface RegisterRepository {
    suspend fun signup(
        user: User
    ): AuthResult<Result<Boolean>, RemoteError>
}