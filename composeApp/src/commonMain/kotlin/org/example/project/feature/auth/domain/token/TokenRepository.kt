package org.example.project.feature.auth.domain.token

interface TokenRepository {
    fun refreshToken()
}