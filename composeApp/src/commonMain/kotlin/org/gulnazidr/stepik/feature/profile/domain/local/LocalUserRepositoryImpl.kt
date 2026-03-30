package org.gulnazidr.stepik.feature.profile.domain.local

import org.gulnazidr.stepik.core.datastore.user.source.UserDataStoreRepository
import org.gulnazidr.stepik.core.model.User

class LocalUserRepositoryImpl(
    private val userDataStoreRepository: UserDataStoreRepository
): LocalUserRepository {
    override suspend fun saveUser(user: User): Result<Boolean>{
        return userDataStoreRepository.saveCurrentUser(user)
    }

    override suspend fun getUser(): Result<User>{
        return userDataStoreRepository.getCurrentUser()
    }

    override suspend fun deleteUser(): Result<Boolean>{
        return userDataStoreRepository.deleteUser()
    }
}