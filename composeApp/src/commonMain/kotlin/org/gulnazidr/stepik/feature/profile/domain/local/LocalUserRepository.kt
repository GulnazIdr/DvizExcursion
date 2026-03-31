package org.gulnazidr.stepik.feature.profile.domain.local

import org.gulnazidr.stepik.core.model.User

interface LocalUserRepository {
    suspend fun saveUser(user: User): Result<Boolean>

    suspend fun getUser(): Result<User>

    suspend fun deleteUser(): Result<Boolean>
}