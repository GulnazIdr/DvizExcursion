package org.example.project.feature.auth.domain

import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.auth.domain.token.TokenRepository
import org.example.project.feature.auth.domain.token.TokenStorage
import org.example.project.feature.auth.presentation.AppAuthHandler

class TokenRepositoryImpl(
    private val appAuthHandler: AppAuthHandler,
    private val tokenDataRepository: TokenDataRepository
): TokenRepository {

    override suspend fun refreshToken() {
        val refreshToken = tokenDataRepository.getRefreshToken()
        if (refreshToken != null) {
            appAuthHandler.performRefreshToken(refreshToken)
        }
    }

    override fun logout() {
        TokenStorage.accessToken = null
        TokenStorage.refreshToken = null
        TokenStorage.idToken = null
        tokenDataRepository.deleteAll()
    }
}