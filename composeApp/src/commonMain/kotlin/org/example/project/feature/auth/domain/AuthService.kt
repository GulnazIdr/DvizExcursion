package org.example.project.feature.auth.domain

interface AuthService {
    suspend fun authenticate(): AuthResult<String, RemoteError>
}

expect class PlatformAuthService : AuthService {
    override suspend fun authenticate(): AuthResult<String, RemoteError>
}

expect fun createAuthService(): AuthService