plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeHotReload) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false

    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)

    kotlin("plugin.serialization") version "2.3.0"

    id("io.gitlab.arturbosch.detekt") version "1.23.8"
    id("ru.ok.tracer").version("1.2.3")
    id("com.google.gms.google-services") version "4.4.4" apply false
    id("com.google.firebase.crashlytics") version "3.0.6" apply false
}

tracer {
    create("defaultConfig") {
        pluginToken = "NEM6KIluvwx0eJezHF2BkPsLmermPYqEHdMJuxUYa0i1"
        appToken = "rdpmwvD7NZTzB8sZrdPUE37pfZ2FHsfkGS2wEP46oGp"

        uploadMapping = true
        uploadNativeSymbols = true
        uploadRetryCount = 2
        additionalLibrariesPath = projectDir.toString() + "/aVeryNonstandardLibsDirectory"
    }
}