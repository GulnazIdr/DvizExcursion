package org.example.project.feature.onboarding.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import org.example.project.feature.onboarding.data.impl.BoardingBoardingDataStoreImpl
import org.example.project.feature.onboarding.data.source.BoardingDataStore
import org.koin.core.qualifier.named
import org.koin.dsl.module

val boardingDataStoreModule = module {
    factory<BoardingDataStore> {
        BoardingBoardingDataStoreImpl(
            get(named("data_pref"))
        )
    }
}