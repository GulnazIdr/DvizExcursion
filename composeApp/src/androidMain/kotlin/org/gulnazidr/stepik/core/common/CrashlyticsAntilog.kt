package org.gulnazidr.stepik.core.common

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.crashlytics
import io.github.aakira.napier.Antilog
import io.github.aakira.napier.LogLevel

class CrashlyticsAntilog : Antilog() {
    override fun performLog(
        priority: LogLevel,
        tag: String?,
        throwable: Throwable?,
        message: String?
    ) {

        when (priority) {
            LogLevel.VERBOSE -> Firebase.crashlytics.log("VERBOSE: $message")
            LogLevel.DEBUG -> Firebase.crashlytics.log("DEBUG: $message")
            LogLevel.INFO -> Firebase.crashlytics.log("INFO: $message")
            LogLevel.WARNING -> Firebase.crashlytics.log("WARN: $message")
            LogLevel.ERROR -> {
                Firebase.crashlytics.log("ERROR: $message")
                throwable?.let {
                    Firebase.crashlytics.recordException(it)
                }
            }
            LogLevel.ASSERT -> Firebase.crashlytics.log("ASSERT: $message")
        }
    }
}