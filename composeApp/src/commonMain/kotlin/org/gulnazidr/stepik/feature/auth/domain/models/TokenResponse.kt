package org.gulnazidr.stepik.feature.auth.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val access_token: String,
    val refresh_token: String? = null,
    val id_token: String? = null,
    val token_type: String,
    val expires_in: Int
)