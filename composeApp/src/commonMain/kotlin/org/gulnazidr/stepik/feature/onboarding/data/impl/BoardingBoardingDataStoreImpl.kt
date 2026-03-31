package org.gulnazidr.stepik.feature.onboarding.data.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.gulnazidr.stepik.core.domain.cancellationRunCatching
import org.gulnazidr.stepik.feature.onboarding.data.source.BoardingDataStore

class BoardingBoardingDataStoreImpl(
    private val dataStorePref: DataStore<Preferences>
) : BoardingDataStore {
    val BOARDING_VIEWED = booleanPreferencesKey("boardingViewState")

    override suspend fun setOnBoardingViewed(): Result<Boolean> {
        return cancellationRunCatching {
            dataStorePref.edit { preferences ->
                preferences[BOARDING_VIEWED] = true
            }
            true
        }
    }

    override fun getOnBoardingViewed(): Result<Flow<Boolean>> {
        return cancellationRunCatching {
            dataStorePref.data.map { preferences ->
                preferences[BOARDING_VIEWED] ?: false
            }
        }
    }

    override suspend fun deleteData(): Result<Boolean> {
        return cancellationRunCatching {
            dataStorePref.edit { it.clear() }
            true
        }
    }
}