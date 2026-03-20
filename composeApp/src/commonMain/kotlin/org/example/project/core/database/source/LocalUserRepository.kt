package org.example.project.core.database.source

import org.example.project.core.model.User

interface LocalUserRepository {
    suspend fun saveUser(user: User): Boolean

    suspend fun updateUser(user: User): Boolean

    suspend fun getUser(): User?

    suspend fun deleteUser(): Boolean
}