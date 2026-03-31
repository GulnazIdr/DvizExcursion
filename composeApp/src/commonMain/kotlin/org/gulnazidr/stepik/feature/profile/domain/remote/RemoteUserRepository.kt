package org.gulnazidr.stepik.feature.profile.domain.remote

import org.gulnazidr.stepik.core.model.User

interface RemoteUserRepository {
    suspend fun getCurrentUser(): Result<User>
    suspend fun getUserList(idList: List<Int>): Result<List<User>>
}