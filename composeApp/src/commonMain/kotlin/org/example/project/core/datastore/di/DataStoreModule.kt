package org.example.project.core.datastore.di

import org.example.project.core.datastore.impl.DataStoreRepositoryImpl
import org.example.project.core.datastore.source.DataStoreRepository
import org.koin.core.module.Module
import org.koin.dsl.module

expect val dataStoreModule: Module

val dataStoreRepositoryModule = module {
    factory<DataStoreRepository> { DataStoreRepositoryImpl(get()) }
}

internal val DATA_STORE_FILE_NAME = "prefs.preferences_pb"