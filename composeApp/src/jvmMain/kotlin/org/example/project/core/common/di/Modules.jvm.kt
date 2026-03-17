package org.example.project.core.common.di

import org.example.project.feature.auth.domain.DesktopViewmodel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val authModule: Module
    get() = module {
        viewModelOf(::DesktopViewmodel)
    }