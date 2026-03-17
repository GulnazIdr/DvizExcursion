package org.example.project.feature.profile.di

import org.example.project.core.database.LocalUserRepository
import org.example.project.feature.profile.ProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val profileModule = module {
    viewModelOf(::ProfileViewModel)
}