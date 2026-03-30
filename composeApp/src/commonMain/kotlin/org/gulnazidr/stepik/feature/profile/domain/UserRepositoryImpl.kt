package org.gulnazidr.stepik.feature.profile.domain

import io.github.aakira.napier.Napier
import org.gulnazidr.stepik.core.common.result.executeApiRequest
import org.gulnazidr.stepik.core.domain.user.UserRepository
import org.gulnazidr.stepik.core.model.User
import org.gulnazidr.stepik.core.network.ktor.models.DataWrapping
import org.gulnazidr.stepik.feature.profile.domain.local.LocalUserRepository
import org.gulnazidr.stepik.feature.profile.domain.remote.RemoteUserRepository

class UserRepositoryImpl(
    private val remoteUserRepository: RemoteUserRepository,
    private val localUserRepository: LocalUserRepository
): UserRepository {
    override suspend fun getCurrentUser(): Result<DataWrapping<User>> {
        return remoteUserRepository.getCurrentUser().executeApiRequest(
            onSuccessAction = {
                Napier.wtf("user2 $it")
                localUserRepository.saveUser(it) },
            getFromCache = { localUserRepository.getUser() }
        )
    }

    override suspend fun saveUser(user: User): Boolean {
        return localUserRepository.saveUser(user).fold(
            onSuccess = { it },
            onFailure = { false }
        )
    }

    override suspend fun getUserList(idList: List<Int>): Result<DataWrapping<List<User>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(): Boolean {
        return localUserRepository.deleteUser().fold(
            onSuccess = { it },
            onFailure = { false }
        )
    }
}