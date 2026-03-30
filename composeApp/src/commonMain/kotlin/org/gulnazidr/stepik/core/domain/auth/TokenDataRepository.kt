package org.gulnazidr.stepik.core.domain.auth

interface TokenDataRepository {
    fun saveAccessToken(token: String): Boolean
    fun getAccessToken(): String?
    fun saveRefreshToken(token: String?): Boolean
    fun getRefreshToken(): String?
    fun deleteAll(): Boolean
}