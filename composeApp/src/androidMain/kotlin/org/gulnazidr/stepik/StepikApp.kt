package org.gulnazidr.stepik

import android.app.Application
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.gulnazidr.stepik.core.common.CrashlyticsAntilog
import org.gulnazidr.stepik.core.common.di.initKoin
import org.koin.android.ext.koin.androidContext
import ru.ok.tracer.BuildConfig

class StepikApp: Application(){
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@StepikApp)
        }

        if (BuildConfig.DEBUG) {
            FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = false
            Napier.base(DebugAntilog())
        } else {
            FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = true
            Napier.base(CrashlyticsAntilog())
            Napier.e("Test error message", Exception("Test exception"))
        }
    }

}