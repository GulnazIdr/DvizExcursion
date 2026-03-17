package org.example.project.feature.auth.domain

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimbusds.oauth2.sdk.AuthorizationCode
import com.nimbusds.oauth2.sdk.AuthorizationCodeGrant
import com.nimbusds.oauth2.sdk.AuthorizationRequest
import com.nimbusds.oauth2.sdk.Scope
import com.nimbusds.oauth2.sdk.TokenRequest
import com.nimbusds.oauth2.sdk.TokenResponse
import com.nimbusds.oauth2.sdk.auth.ClientSecretBasic
import com.nimbusds.oauth2.sdk.auth.Secret
import com.nimbusds.oauth2.sdk.id.ClientID
import com.sun.net.httpserver.HttpServer
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import java.awt.Desktop

actual class PlatformAuthService : AuthService {
    actual override suspend fun authenticate(): AuthResult<String, RemoteError> {
        return try {
            val authRequest = AuthorizationRequest.Builder(
                AuthConfigDesktop.RESPONSE_TYPE,
                AuthConfigDesktop.CLIENT_ID
            )
                .scope(Scope("read", "write"))
                .redirectionURI(AuthConfigDesktop.REDIRECT_URL)
                .endpointURI(AuthConfigDesktop.ENDPOINT_URL)
                .build()

            Desktop.getDesktop().browse(authRequest.toURI())

            val server = HttpServer.create(java.net.InetSocketAddress(8080), 0)
            var authorizationCode: AuthorizationCode? = null

            server.createContext("/gulnazstepikcallback") { exchange ->
                val query = exchange.requestURI.query
                val params = query.split("&").associate {
                    val (k, v) = it.split("=")
                    k to v
                }
                authorizationCode = AuthorizationCode(params["code"])
                val response = "Authorization successful! You can close this window."
                exchange.sendResponseHeaders(200, response.toByteArray().size.toLong())
                exchange.responseBody.use { os -> os.write(response.toByteArray()) }
                server.stop(0)
            }
            server.start()

            while (authorizationCode == null) {
                Thread.sleep(100)
            }

            val tokenRequest = TokenRequest(
                AuthConfigDesktop.TOKENT_URL,
                ClientSecretBasic(
                    ClientID(AuthConfigDesktop.CLIENT_ID),
                    Secret(AuthConfig.CLIENT_SECRET)
                ),
                AuthorizationCodeGrant(authorizationCode, AuthConfigDesktop.REDIRECT_URL),
                Scope("read", "write")
            )

            val tokenResponse = TokenResponse.parse(tokenRequest.toHTTPRequest().send())

            if (tokenResponse.indicatesSuccess()) {
                val successResponse = tokenResponse.toSuccessResponse()
                val accessToken = successResponse.tokens.accessToken.value
                AuthResult.Success(accessToken)
            } else {
                val errorResponse = tokenResponse.toErrorResponse()
                AuthResult.Error(RemoteError.WRONG_CREDENTIALS)
            }
        } catch (e: Exception) {
            AuthResult.Error(RemoteError.WRONG_CREDENTIALS)
        }
    }
}

actual fun createAuthService(): AuthService {
    return PlatformAuthService()
}



class DesktopViewmodel(): ViewModel(){
    val service = PlatformAuthService()
    fun auth(){
        viewModelScope.launch {
            service.authenticate()
        }
    }
}