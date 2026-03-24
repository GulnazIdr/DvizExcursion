package org.example.project.core.datastore.user.source

import org.example.project.core.model.User

interface UserDataStoreRepository {
    suspend fun saveCurrentUser(user: User)
    suspend fun getCurrentUser(): User
    suspend fun deleteUser()
}