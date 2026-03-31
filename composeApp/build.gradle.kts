import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
    id("org.jetbrains.kotlin.plugin.serialization")
    id("io.gitlab.arturbosch.detekt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.accompanist.swiperefresh)

            implementation(libs.androidx.room.sqlite.wrapper)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)

            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.appauth)

            implementation(libs.ktor.client.android)
            implementation(libs.kotlinx.coroutines.android)

            implementation(libs.kvault)

            implementation(project.dependencies.platform(libs.tracer.platform))
            implementation(libs.tracer.crash.report)
            implementation(libs.tracer.crash.report.native)
            implementation(libs.tracer.heap.dumps)
            implementation(libs.tracer.disk.usage)


            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:34.7.0"))
            implementation(libs.google.firebase.analytics)
            implementation(libs.google.firebase.crashlytics)
        }
        commonMain.dependencies {
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)

            implementation(libs.ui.backhandler)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.logging)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.serialization.kotlinx.json)

            api(libs.datastore.preferences)
            api(libs.datastore)

            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.lifecycle.viewmodel)
            implementation(libs.navigation.compose)

            implementation(libs.coil.compose)
            implementation(libs.coil.network.okhttp)

            implementation(libs.napier)

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(libs.oauth2.oidc.sdk)
            implementation(libs.nimbus.jose.jwt)

            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.androidx.ui.desktop)

        }
    }
}

android {
    namespace = "org.gulnazidr.stepik"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.gulnazidr.stepik"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        manifestPlaceholders["appAuthRedirectScheme"] = "http"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("C:\\Users\\gulnaz\\keystore.jks")
            storePassword = "stepGul18!"
            keyAlias = "key0"
            keyPassword = "step"
        }
    }

    buildTypes {
//        debug {
//            signingConfig = signingConfigs.findByName("debug")
//            isMinifyEnabled = false
//        }

        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}




dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)

    debugImplementation(libs.compose.uiTooling)
    debugImplementation(libs.leakcanary.android)
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    source.setFrom(files("src/main/kotlin"))
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
    }
}

compose.desktop {
    application {
        mainClass = "org.example.org.stepik.org.gulnazidr.stepik.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.example.org.stepik.org.gulnazidr.stepik"
            packageVersion = "1.0.0"
        }
    }
}


