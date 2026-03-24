package org.example.project.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.io.files.Path
import okio.Path.Companion.toPath
import org.example.project.core.common.di.DATA_STORE_FILE_NAME
import org.koin.core.module.Module
import org.koin.dsl.module

actual val dataStoreModule: Module
    get() = module {
        single<DataStore<Preferences>> {
            PreferenceDataStoreFactory.createWithPath(
                produceFile = { DATA_STORE_FILE_NAME.toPath() }
            )
        }
    }