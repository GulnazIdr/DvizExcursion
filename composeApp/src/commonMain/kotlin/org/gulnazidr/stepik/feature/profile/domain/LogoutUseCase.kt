package org.gulnazidr.stepik.feature.profile.domain

import org.gulnazidr.stepik.core.domain.auth.TokenRepository
import org.gulnazidr.stepik.feature.course_catalog.domain.local.LocalCourseRepository
import org.gulnazidr.stepik.feature.profile.domain.local.LocalUserRepository
import org.koin.compose.getKoin

class LogoutUseCase(
    private val localUserRepository: LocalUserRepository,
    private val localCourseRepository: LocalCourseRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(): Boolean{
        return localUserRepository.deleteUser().isSuccess &&
        localCourseRepository.deleteCourse() &&
        tokenRepository.logout()
    }
}