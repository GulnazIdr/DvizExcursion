package org.example.project.feature.onboarding.domain

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setOnBoardingViewed()
    fun getOnBoardingViewed(): Flow<Boolean>
    suspend fun deleteData()

    suspend fun setLoggedIn()
    fun getLoggedInState(): Flow<Boolean>

    suspend fun setCurrentUserId(id: Int)
    suspend fun getCurrentUserId(): Int?
}