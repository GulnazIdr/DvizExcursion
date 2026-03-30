package org.gulnazidr.stepik.feature.auth.domain

import androidx.core.net.toUri
import net.openid.appauth.AuthorizationServiceConfiguration
import org.gulnazidr.stepik.feature.auth.domain.models.AuthConfig

object AppAuth {
    val authServiceConfig = AuthorizationServiceConfiguration(
        AuthConfig.AUTH_URI.toUri(),
        AuthConfig.TOKEN_URI.toUri(),
        null,
        null
    )
}