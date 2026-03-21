package org.example.project.feature.profile.domain

import kotlinx.coroutines.flow.first
import org.example.project.core.database.source.LocalUserRepository
import org.example.project.core.model.User
import org.example.project.feature.onboarding.domain.DataStoreRepository

class UpdateUserUseCase(
    private val dataStoreRepository: DataStoreRepository,
    private val localUserRepository: LocalUserRepository
) {
    suspend operator fun invoke(user: User) {
        val userId = dataStoreRepository.getCurrentUserId().first()
        if (userId != null) {
            localUserRepository.updateUser(user.copy(id = userId))
        }
    }
}