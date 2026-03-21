package org.example.project.feature.onboarding.domain

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun setOnBoardingViewed()
    fun getOnBoardingViewed(): Flow<Boolean>
    suspend fun deleteData()

    suspend fun setCurrentUserId(id: Int)
    fun getCurrentUserId(): Flow<Int?>
}