package org.example.project.feature.auth.domain.token

interface TokenRepository {
    suspend fun refreshToken(): Boolean
    fun logout()
}