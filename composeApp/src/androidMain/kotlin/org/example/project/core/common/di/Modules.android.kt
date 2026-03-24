package org.example.project.core.common.di

import org.example.project.feature.auth.domain.AppAuthHandler
import org.example.project.feature.auth.presentation.AuthViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun platformModule(appAuthHandler: AppAuthHandler) = module {
    single { appAuthHandler }
}
