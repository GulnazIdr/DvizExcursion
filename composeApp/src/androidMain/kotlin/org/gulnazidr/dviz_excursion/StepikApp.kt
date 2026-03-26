package org.gulnazidr.dviz_excursion

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.project.core.common.di.initKoin
import org.koin.android.ext.koin.androidContext

class StepikApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@StepikApp)
        }

        Napier.base(DebugAntilog())
    }
}