package org.gulnazidr.stepik.feature.auth.domain.registration

import org.gulnazidr.stepik.feature.auth.domain.result.AuthResult
import org.gulnazidr.stepik.feature.auth.domain.result.RemoteError
import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.feature.auth.domain.result.AuthError

interface RegisterRepository {
    suspend fun signup(
        user: User
    ): AuthResult<Result<Boolean>, AuthError>
}