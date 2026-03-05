package org.example.project.di

import org.example.project.data.LoginRepositoryImpl
import org.example.project.data.PostRepositoryImpl
import org.example.project.data.RegisterRepositoryImpl
import org.example.project.domain.auth.login.LoginRepository
import org.example.project.domain.auth.login.LoginUseCase
import org.example.project.domain.auth.registration.RegisterRepository
import org.example.project.domain.post.PostRepository
import org.example.project.presentation.auth.LoginViewModel
import org.example.project.presentation.auth.RegistrationViewmodel
import org.example.project.presentation.auth.models.RegistrationUiState
import org.example.project.presentation.main.PostViewModel
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