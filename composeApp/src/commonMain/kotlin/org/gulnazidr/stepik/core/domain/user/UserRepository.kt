package org.gulnazidr.stepik.core.domain.user

import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.core.network.ktor.models.DataWrapping

interface UserRepository {
    suspend fun getCurrentUser(): Result<DataWrapping<User>>
    suspend fun getUserList(idList: List<Int>): Result<DataWrapping<List<User>>>

    suspend fun saveUser(user: User): Boolean

    suspend fun deleteUser(): Boolean
}