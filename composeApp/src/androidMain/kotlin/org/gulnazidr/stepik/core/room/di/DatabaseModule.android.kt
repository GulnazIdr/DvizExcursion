package org.gulnazidr.stepik.core.room.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.gulnazidr.stepik.core.room.StepikDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val databaseModule: Module
    get() = module {
        single{
            Room.databaseBuilder<StepikDatabase>(
                context = androidContext(),
                name = androidContext().getDatabasePath("stepik.db").absolutePath
            )
                .addMigrations()
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }