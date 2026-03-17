package org.example.project.feature.auth.domain

import com.nimbusds.oauth2.sdk.ResponseType
import com.nimbusds.oauth2.sdk.id.ClientID
import java.net.URI

object AuthConfigDesktop {
    val RESPONSE_TYPE = ResponseType.CODE
    val CLIENT_ID = ClientID(AuthConfig.CLIENT_ID)
    val REDIRECT_URL = URI(AuthConfig.CALLBACK_URL)
    val ENDPOINT_URL = URI(AuthConfig.AUTH_URI)
    val TOKENT_URL = URI(AuthConfig.TOKEN_URI)
    val CLIENT_SECRET = URI(AuthConfig.CLIENT_SECRET)
}