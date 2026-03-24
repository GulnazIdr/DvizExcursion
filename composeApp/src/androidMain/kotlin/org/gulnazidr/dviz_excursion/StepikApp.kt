package org.gulnazidr.dviz_excursion

import android.app.Application
import android.content.Context
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.example.project.core.common.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class StepikApp: Application() {
    override fun onCreate() {
        super.onCreate()


        Napier.base(DebugAntilog())
    }
}