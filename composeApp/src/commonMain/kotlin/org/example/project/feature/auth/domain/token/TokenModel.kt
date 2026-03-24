package org.example.project.feature.auth.domain.token

data class TokensModel(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String
)