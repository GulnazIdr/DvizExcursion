package org.gulnazidr.stepik.core.datastore.user.source

import org.gulnazidr.stepik.core.model.User

interface UserDataStoreRepository {
    suspend fun saveCurrentUser(user: User): Result<Boolean>
    suspend fun getCurrentUser(): Result<User>
    suspend fun deleteUser(): Result<Boolean>
}