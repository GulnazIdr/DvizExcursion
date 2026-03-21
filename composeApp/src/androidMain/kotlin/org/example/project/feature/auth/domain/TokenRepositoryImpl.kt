package org.example.project.feature.auth.domain

import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.auth.domain.token.TokenRepository

class TokenRepositoryImpl(
    private val appAuthHandler: AppAuthHandler,
    private val tokenDataRepository: TokenDataRepository
): TokenRepository {
    override fun refreshToken() {
        val refreshToken = tokenDataRepository.getRefreshToken()
        if (refreshToken != null) {
            appAuthHandler.getRefreshTokenRequest(refreshToken)
        }
    }
}