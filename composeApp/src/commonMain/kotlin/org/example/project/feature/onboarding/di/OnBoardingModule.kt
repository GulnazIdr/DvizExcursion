package org.example.project.feature.onboarding.di

import org.example.project.feature.onboarding.data.impl.BoardingBoardingDataStoreImpl
import org.example.project.feature.onboarding.data.source.BoardingDataStore
import org.koin.dsl.module

val boardingDataStoreModule = module {
    factory<BoardingDataStore> { BoardingBoardingDataStoreImpl(get()) }
}