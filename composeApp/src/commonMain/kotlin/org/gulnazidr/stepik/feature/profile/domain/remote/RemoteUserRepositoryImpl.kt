package org.gulnazidr.stepik.feature.profile.domain.remote

import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.core.network.ktor.user.source.KtorUserRepository

class RemoteUserRepositoryImpl(
    private val ktorUserRepository: KtorUserRepository
): RemoteUserRepository {
    override suspend fun getCurrentUser(): Result<User> {
        return ktorUserRepository.getCurrentUser()
    }

    override suspend fun getUserList(idList: List<Int>): Result<List<User>> {
        return ktorUserRepository.getUserList(idList)
    }
}