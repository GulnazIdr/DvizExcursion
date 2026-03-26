package org.example.project.core.datastore.di

import org.koin.core.module.Module
expect val dataStoreModule: Module
expect val userDataStoreModule: Module

internal val DATA_STORE_FILE_NAME = "prefs.preferences_pb"
internal val USER_DATA_STORE_FILE_NAME = "user_prefs.preferences_pb"