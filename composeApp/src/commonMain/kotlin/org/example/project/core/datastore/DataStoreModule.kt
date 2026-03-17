package org.example.project.core.datastore

import org.example.project.feature.onboarding.data.DataStoreRepositoryImpl
import org.example.project.feature.onboarding.domain.DataStoreRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val dataStoreModule: Module

val dataStoreRepositoryModule = module {
    singleOf(::DataStoreRepositoryImpl).bind<DataStoreRepository>()
}

internal val DATA_STORE_FILE_NAME = "prefs.preferences_pb"