package org.gulnazidr.stepik.feature.auth.di

import org.gulnazidr.stepik.core.domain.auth.TokenRepository
import org.gulnazidr.stepik.feature.auth.data.LoginRepositoryImpl
import org.gulnazidr.stepik.feature.auth.data.RegisterRepositoryImpl
import org.gulnazidr.stepik.feature.auth.domain.login.LoginErrorUseCase
import org.gulnazidr.stepik.feature.auth.domain.login.LoginRepository
import org.gulnazidr.stepik.feature.auth.domain.login.LoginUseCase
import org.gulnazidr.stepik.feature.auth.domain.registration.RegisterRepository
import org.gulnazidr.stepik.feature.auth.domain.registration.RegisterUseCase
import org.gulnazidr.stepik.feature.auth.domain.token.TokenRepositoryImpl
import org.gulnazidr.stepik.feature.auth.presentation.login.LoginViewModel
import org.gulnazidr.stepik.feature.auth.presentation.register.RegistrationViewmodel
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
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
    factoryOf(::RegisterUseCase)
}

expect val secureTokenStorageModule: Module

expect val tokenDataRepositoryModule: Module

val tokenRepositoryModule: Module
    get() = module {
        factory<TokenRepository>{TokenRepositoryImpl(get(), get())}
    }

expect val authModule: Module