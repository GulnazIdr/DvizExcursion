package org.example.project.domain.login

interface LoginRepository {
    suspend fun login(name: String, password: String): Result<Boolean>
}