package org.example.project.feature.profile.domain

import org.example.project.core.database.source.LocalCourseRepository
import org.example.project.core.database.source.LocalUserRepository
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.core.datastore.source.DataStoreRepository
import org.example.project.feature.auth.domain.token.TokenRepository

class LogoutUseCase(
    private val localUserRepository: LocalUserRepository,
    private val localCourseRepository: LocalCourseRepository,
    private val dataStoreRepository: DataStoreRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(){
        localUserRepository.deleteUser()
        localCourseRepository.deleteCourse()
        dataStoreRepository.deleteData()
        tokenRepository.logout()
    }
}