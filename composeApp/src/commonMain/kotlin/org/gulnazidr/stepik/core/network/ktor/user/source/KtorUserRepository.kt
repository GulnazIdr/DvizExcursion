package org.gulnazidr.stepik.core.network.ktor.user.source

import org.gulnazidr.stepik.core.model.User

interface KtorUserRepository {
    suspend fun getCurrentUser(): Result<User>
    suspend fun getUserList(idList: List<Int>): Result<List<User>>
}