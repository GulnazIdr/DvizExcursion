package org.gulnazidr.dviz_excursion.presentation

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.project.core.common.di.initKoin

class RedditApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        Napier.base(DebugAntilog())
    }
}