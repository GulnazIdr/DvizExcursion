package org.gulnazidr.stepik.core.network.ktor.user.di

import org.gulnazidr.stepik.core.network.ktor.user.impl.KtorUserRepositoryImpl
import org.gulnazidr.stepik.core.network.ktor.user.source.KtorUserRepository
import org.koin.dsl.module

val userKtorRepositoryModule = module {
    factory<KtorUserRepository> { KtorUserRepositoryImpl(get(), get()) }
}