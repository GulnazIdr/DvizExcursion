package org.example.project.feature.profile.domain

import org.example.project.core.database.source.LocalCourseRepository
import org.example.project.core.database.source.LocalUserRepository
import org.example.project.feature.onboarding.domain.DataStoreRepository

class LogoutUseCase(
    private val localUserRepository: LocalUserRepository,
    private val localCourseRepository: LocalCourseRepository,
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(){
//        localUserRepository.deleteUser()
//        localCourseRepository.deleteCourse()
//        dataStoreRepository.deleteData()
    }
}