package org.example.project.feature.profile.domain

import org.example.project.core.database.source.LocalUserRepository
import org.example.project.core.model.User
import org.example.project.feature.onboarding.data.source.BoardingDataStore

class UpdateUserUseCase(
    private val boardingDataStore: BoardingDataStore,
    private val localUserRepository: LocalUserRepository
) {
    suspend operator fun invoke(user: User) {
        localUserRepository.updateUser(user)
    }
}