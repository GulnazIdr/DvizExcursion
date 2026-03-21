package org.example.project.feature.auth.data

import com.liftric.kvault.KVault
import org.example.project.feature.auth.domain.token.TokenDataRepository

class TokenDataRepositoryImpl(
    private val store: KVault
): TokenDataRepository {
    val ACCESS_TOKEN = "access_token"
    val REFRESH_TOKEN = "refresh_token"

    override fun saveAccessToken(token: String): Boolean {
        return store.set(key = ACCESS_TOKEN, stringValue = token)
    }

    override fun getAccessToken(): String? {
        return store.string(forKey = ACCESS_TOKEN)
    }

    override fun saveRefreshToken(token: String?): Boolean {
        if (token == null) return false
        return store.set(key = REFRESH_TOKEN, stringValue = token)
    }

    override fun getRefreshToken(): String? {
        return store.string(forKey = REFRESH_TOKEN)
    }
}