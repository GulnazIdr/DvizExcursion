package org.gulnazidr.stepik.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okio.Path.Companion.toPath
import org.gulnazidr.stepik.core.datastore.user.UserPreferencesSerializer
import org.gulnazidr.stepik.core.datastore.user.DataStoreUserSerial
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val dataStoreModule: Module = module {
    single<DataStore<Preferences>>(named("data_pref")) {
        PreferenceDataStoreFactory.createWithPath(
            produceFile = {
                androidContext().filesDir
                    .resolve(DATA_STORE_FILE_NAME).absolutePath.toPath()
            }
        )
    }
}
actual val userDataStoreModule: Module
    get() = module {
        single<DataStore<DataStoreUserSerial>>(named("data_user")){
            DataStoreFactory.create(
                serializer = UserPreferencesSerializer,
                corruptionHandler = ReplaceFileCorruptionHandler(
                    produceNewData = { DataStoreUserSerial()}
                ),
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
                produceFile = {androidContext().dataStoreFile(USER_DATA_STORE_FILE_NAME)}
            )
        }
    }