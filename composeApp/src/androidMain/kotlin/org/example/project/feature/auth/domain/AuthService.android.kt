package org.example.project.feature.auth.domain

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

actual class PlatformAuthService: AuthService {
    private var token: String? = null
    fun settoken(token: String){
        this.token = token

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            authenticate()
        }
    }

    actual override suspend fun authenticate(): AuthResult<String, RemoteError> {

        return if (token == null) AuthResult.Error(RemoteError.WRONG_CREDENTIALS)
        else AuthResult.Success(token!!)
    }
}

actual fun createAuthService(): AuthService {
    return PlatformAuthService()
}
