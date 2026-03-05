package org.example.project.di

import org.example.project.data.remote.LoginRepositoryImpl
import org.example.project.data.remote.PostRepositoryImpl
import org.example.project.data.remote.RegisterRepositoryImpl
import org.example.project.domain.auth.login.LoginRepository
import org.example.project.domain.auth.login.LoginUseCase
import org.example.project.domain.auth.registration.RegisterRepository
import org.example.project.domain.post.PostRepository
import org.example.project.presentation.auth.LoginViewModel
import org.example.project.presentation.auth.RegistrationViewmodel
import org.example.project.presentation.main.PostViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val postModule = module {
    singleOf(::PostRepositoryImpl).bind<PostRepository>()
    viewModelOf(::PostViewModel)
}

val loginModule = module{
    viewModelOf(::LoginViewModel)
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
    singleOf(::LoginUseCase)
}

val registerModule = module{
    viewModelOf(::RegistrationViewmodel)
    singleOf(::RegisterRepositoryImpl).bind<RegisterRepository>()
}

expect val dataStoreModule: Module
internal val DATA_STORE_FILE_NAME = "prefs.preferences_"