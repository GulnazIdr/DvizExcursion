package org.gulnazidr.stepik.feature.profile.di

import org.gulnazidr.stepik.core.common.di.UserSessionScope
import org.gulnazidr.stepik.core.domain.user.UserRepository
import org.gulnazidr.stepik.feature.profile.domain.FetchCurrentUserUseCase
import org.gulnazidr.stepik.feature.profile.domain.LogoutUseCase
import org.gulnazidr.stepik.feature.profile.domain.UserRepositoryImpl
import org.gulnazidr.stepik.feature.profile.domain.local.LocalUserRepository
import org.gulnazidr.stepik.feature.profile.domain.local.LocalUserRepositoryImpl
import org.gulnazidr.stepik.feature.profile.domain.remote.RemoteUserRepository
import org.gulnazidr.stepik.feature.profile.domain.remote.RemoteUserRepositoryImpl
import org.gulnazidr.stepik.feature.profile.presentation.ProfileViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule = module {
    viewModel { (userUseCase: FetchCurrentUserUseCase) ->
        ProfileViewModel(
            fetchCurrentUserUseCase = userUseCase,
            logoutUseCase = get(),
            userUiToUserMapper = get()
        )
    }
    //viewModelOf(::ProfileViewModel)
    factoryOf(::LogoutUseCase)
}

val userModule = module{
    factory<LocalUserRepository> { LocalUserRepositoryImpl(get()) }
    factory<RemoteUserRepository> { RemoteUserRepositoryImpl(get()) }
    factory<UserRepository> {
        UserRepositoryImpl(
        get(),
        get())
    }
    scope(UserSessionScope.USER_SESSION_SCOPE) {
        scoped { FetchCurrentUserUseCase(get()) }
    }
}