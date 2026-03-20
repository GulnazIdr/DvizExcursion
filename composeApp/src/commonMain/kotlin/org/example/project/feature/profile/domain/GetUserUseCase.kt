package org.example.project.feature.profile.domain

import org.example.project.core.database.source.LocalUserRepository
import org.example.project.core.model.User

class GetUserUseCase(
    private val localUserRepository: LocalUserRepository
) {
    suspend operator fun invoke(): User?  = localUserRepository.getUser()
}