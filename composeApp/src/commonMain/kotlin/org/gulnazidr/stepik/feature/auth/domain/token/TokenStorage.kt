package org.gulnazidr.stepik.feature.auth.domain.token

data object TokenStorage {
    var accessToken: String? = null
    var refreshToken: String? = null
    var idToken: String? = null
    var expiresIn: Int? = null
}