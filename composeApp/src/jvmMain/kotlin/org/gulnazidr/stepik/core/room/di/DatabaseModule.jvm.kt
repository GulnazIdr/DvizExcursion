package org.gulnazidr.stepik.core.room.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import org.gulnazidr.stepik.core.room.StepikDatabase
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File


actual val databaseModule: Module
    get() = module {
        Room.databaseBuilder<StepikDatabase>(
            name = File(System.getProperty("java.io.tmpdir"), "stepik.db").absolutePath,
        )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
    }