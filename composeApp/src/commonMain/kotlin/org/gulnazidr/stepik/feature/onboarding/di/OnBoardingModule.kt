package org.gulnazidr.stepik.feature.onboarding.di

import org.gulnazidr.stepik.feature.onboarding.data.impl.BoardingBoardingDataStoreImpl
import org.gulnazidr.stepik.feature.onboarding.data.source.BoardingDataStore
import org.koin.core.qualifier.named
import org.koin.dsl.module

val boardingDataStoreModule = module {
    factory<BoardingDataStore> {
        BoardingBoardingDataStoreImpl(
            get(named("data_pref"))
        )
    }
}