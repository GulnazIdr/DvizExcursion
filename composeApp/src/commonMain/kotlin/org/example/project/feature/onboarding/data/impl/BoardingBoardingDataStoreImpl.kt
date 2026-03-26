package org.example.project.feature.onboarding.data.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.example.project.feature.onboarding.data.source.BoardingDataStore

class BoardingBoardingDataStoreImpl(
    private val dataStorePref: DataStore<Preferences>
) : BoardingDataStore {
    val BOARDING_VIEWED = booleanPreferencesKey("boardingViewState")

    override suspend fun setOnBoardingViewed() {
        dataStorePref.edit { preferences ->
            preferences[BOARDING_VIEWED] = true
        }
    }

    override fun getOnBoardingViewed(): Flow<Boolean> {
        return dataStorePref.data.map { preferences ->
            preferences[BOARDING_VIEWED] ?: false
        }
    }

    override suspend fun deleteData() {
        dataStorePref.edit { it.clear() }
    }
}