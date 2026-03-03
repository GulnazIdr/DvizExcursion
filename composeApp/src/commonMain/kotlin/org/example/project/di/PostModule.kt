package org.example.project.di

import org.example.project.data.LoginRepositoryImpl
import org.example.project.data.PostRepositoryImpl
import org.example.project.domain.login.LoginRepository
import org.example.project.domain.post.PostRepository
import org.example.project.presentation.login.AuthViewModel
import org.example.project.presentation.main.PostViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val postModule = module {
    singleOf(::PostRepositoryImpl).bind<PostRepository>()
    viewModelOf(::PostViewModel)
}

val authModule = module{
    viewModelOf(::AuthViewModel)
    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()
}