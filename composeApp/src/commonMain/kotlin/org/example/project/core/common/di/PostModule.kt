package org.example.project.core.common.di

import org.example.project.feature.auth.data.LoginRepositoryImpl
import org.example.project.feature.main.data.PostRepositoryImpl
import org.example.project.feature.auth.data.RegisterRepositoryImpl
import org.example.project.feature.auth.domain.login.LoginUseCase
import org.example.project.feature.auth.presentation.login.LoginViewModel
import org.example.project.feature.auth.presentation.register.RegistrationViewmodel
import org.example.project.feature.main.presentation.PostViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val postModule = module {
    singleOf(::PostRepositoryImpl).bind<org.example.project.feature.main.domain.PostRepository>()
    viewModelOf(::PostViewModel)
}

val loginModule = module{
    viewModelOf(::LoginViewModel)
    singleOf(::LoginRepositoryImpl).bind<org.example.project.feature.auth.domain.login.LoginRepository>()
    singleOf(::LoginUseCase)
}

val registerModule = module{
    viewModelOf(::RegistrationViewmodel)
    singleOf(::RegisterRepositoryImpl).bind<org.example.project.feature.auth.domain.registration.RegisterRepository>()
}

expect val dataStoreModule: Module
internal val DATA_STORE_FILE_NAME = "prefs.preferences_"