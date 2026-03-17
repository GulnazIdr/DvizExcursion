package org.example.project.feature.auth.domain

data class TokensModel(
    val accessToken: String,
    val refreshToken: String,
    val idToken: String
)