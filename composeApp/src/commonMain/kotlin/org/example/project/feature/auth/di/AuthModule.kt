package org.example.project.feature.auth.di

import org.example.project.feature.auth.data.LoginRepositoryImpl
import org.example.project.feature.auth.data.RegisterRepositoryImpl
import org.example.project.feature.auth.domain.login.LoginErrorUseCase
import org.example.project.feature.auth.domain.login.LoginRepository
import org.example.project.feature.auth.domain.login.LoginUseCase
import org.example.project.feature.auth.domain.registration.RegisterRepository
import org.example.project.feature.auth.presentation.login.LoginViewModel
import org.example.project.feature.auth.presentation.register.RegistrationViewmodel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)
    factory<LoginRepository> { LoginRepositoryImpl(get()) }
    factoryOf(::LoginErrorUseCase)
    factoryOf(::LoginUseCase)
}

val registerModule = module {
    viewModelOf(::RegistrationViewmodel)
    factory<RegisterRepository> { RegisterRepositoryImpl(get()) }
}

expect val secureTokenStorageModule: Module

expect val tokenDataRepositoryModule: Module

expect val tokenRepositoryModule: Module

expect val authModule: Module