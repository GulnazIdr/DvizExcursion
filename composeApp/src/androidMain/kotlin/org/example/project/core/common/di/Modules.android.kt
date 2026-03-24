package org.example.project.core.common.di

import org.example.project.feature.auth.presentation.AppAuthHandler
import org.koin.dsl.module

fun platformModule(appAuthHandler: AppAuthHandler) = module {
    single { appAuthHandler }
}
