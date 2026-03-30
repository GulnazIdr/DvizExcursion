package org.gulnazidr.stepik.feature.auth.di

import org.gulnazidr.stepik.feature.auth.domain.DesktopViewmodel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val secureTokenStorageModule: Module
    get() = TODO("Not yet implemented")
actual val tokenDataRepositoryModule: Module
    get() = TODO("Not yet implemented")

actual val authModule: Module
    get() = module {
        viewModelOf(::DesktopViewmodel)
    }
