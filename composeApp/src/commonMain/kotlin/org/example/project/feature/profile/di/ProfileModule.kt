package org.example.project.feature.profile.di

import org.example.project.core.database.impl.LocalUserRepositoryImpl
import org.example.project.core.database.source.LocalUserRepository
import org.example.project.feature.profile.presentation.ProfileViewModel
import org.example.project.core.designsystem.ui_logic.mapper.UserUiToUserMapper
import org.example.project.feature.auth.data.LoginRepositoryImpl
import org.example.project.feature.auth.domain.login.LoginRepository
import org.example.project.feature.profile.domain.GetUserUseCase
import org.example.project.feature.profile.domain.LogoutUseCase
import org.example.project.feature.profile.domain.UpdateUserUseCase

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val profileModule = module {
    viewModelOf(::ProfileViewModel)
    factoryOf(::UpdateUserUseCase)
    factoryOf(::GetUserUseCase)
    factoryOf(::LogoutUseCase)
    singleOf(::LocalUserRepositoryImpl).bind<LocalUserRepository>()
}

val userMapperModule = module {
    factory { UserUiToUserMapper() }
}