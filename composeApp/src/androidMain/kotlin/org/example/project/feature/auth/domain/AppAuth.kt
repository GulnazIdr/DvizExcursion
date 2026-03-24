package org.example.project.feature.auth.domain

import androidx.core.net.toUri
import net.openid.appauth.AuthorizationServiceConfiguration

object AppAuth {
    val authServiceConfig = AuthorizationServiceConfiguration(
        AuthConfig.AUTH_URI.toUri(),
        AuthConfig.TOKEN_URI.toUri(),
        null,
        null
    )
}