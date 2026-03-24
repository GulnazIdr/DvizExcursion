package org.example.project.feature.onboarding.data.source

import kotlinx.coroutines.flow.Flow

interface BoardingDataStore {
    suspend fun setOnBoardingViewed()
    fun getOnBoardingViewed(): Flow<Boolean>
    suspend fun deleteData()
}