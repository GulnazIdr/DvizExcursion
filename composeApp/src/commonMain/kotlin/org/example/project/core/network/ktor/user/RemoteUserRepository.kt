package org.example.project.core.network.ktor.user

import org.example.project.core.model.StepikEmail
import org.example.project.core.model.User
import org.example.project.core.network.ktor.models.KtorDataWrapping

interface RemoteUserRepository {
    suspend fun getCurrentUser(): Result<KtorDataWrapping<User>>
    suspend fun getUserList(idList: List<Int>): Result<KtorDataWrapping<List<User>>>
}