package org.gulnazidr.dviz_excursion.presentation

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.project.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RedditApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        Napier.base(DebugAntilog())
    }
}