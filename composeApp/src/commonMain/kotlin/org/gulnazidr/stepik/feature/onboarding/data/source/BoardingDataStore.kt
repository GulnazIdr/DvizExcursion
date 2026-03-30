package org.gulnazidr.stepik.feature.onboarding.data.source

import kotlinx.coroutines.flow.Flow

interface BoardingDataStore {
    suspend fun setOnBoardingViewed(): Result<Boolean>
    fun getOnBoardingViewed(): Result<Flow<Boolean>>
    suspend fun deleteData(): Result<Boolean>
}