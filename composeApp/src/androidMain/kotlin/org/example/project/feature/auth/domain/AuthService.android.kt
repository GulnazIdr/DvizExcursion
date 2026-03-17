package org.example.project.feature.auth.domain

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import org.example.project.feature.auth.presentation.AuthViewModel
import org.koin.compose.viewmodel.koinViewModel

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
