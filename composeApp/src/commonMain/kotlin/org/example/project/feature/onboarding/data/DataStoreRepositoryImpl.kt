package org.example.project.feature.onboarding.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.example.project.feature.onboarding.domain.DataStoreRepository

class DataStoreRepositoryImpl(
    private val dataStorePref: DataStore<Preferences>
) : DataStoreRepository {
    val BOARDING_VIEWED = booleanPreferencesKey("boardingViewState")
    val LOGGED_IN = booleanPreferencesKey("loggedInState")
    val CURRENT_ID = intPreferencesKey("currentUserId")

    override suspend fun setOnBoardingViewed() {
        dataStorePref.edit { preferences ->
            preferences[booleanPreferencesKey(BOARDING_VIEWED.toString())] = true
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

    override suspend fun setLoggedIn() {
        dataStorePref.edit { preferences ->
            preferences[booleanPreferencesKey(LOGGED_IN.toString())] = true
        }
    }

    override fun getLoggedInState(): Flow<Boolean> {
        return dataStorePref.data.map { preferences ->
            preferences[LOGGED_IN] ?: false
        }
    }

    override suspend fun setCurrentUserId(id: Int) {
        dataStorePref.edit { pref ->
            pref[intPreferencesKey(CURRENT_ID.toString())] = id
        }
    }

    override suspend fun getCurrentUserId(): Int? {
        return dataStorePref.data
            .map { pref -> pref[CURRENT_ID] }
            .first()
    }
}