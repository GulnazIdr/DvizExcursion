package org.gulnazidr.stepik.core.domain.auth

interface TokenRepository {
    suspend fun refreshToken(): Boolean
    fun logout(): Boolean
}