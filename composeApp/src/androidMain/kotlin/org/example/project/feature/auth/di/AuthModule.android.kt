package org.example.project.feature.auth.di

import com.liftric.kvault.KVault
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.auth.data.TokenDataRepositoryImpl
import org.example.project.feature.auth.domain.TokenRepositoryImpl
import org.example.project.feature.auth.domain.token.TokenRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val secureTokenStorageModule: Module
    get() = module {
        single { KVault(androidContext(), "authTokenSecureStorage") }
    }
actual val tokenDataRepositoryModule: Module
    get() = module {
        singleOf(::TokenDataRepositoryImpl).bind<TokenDataRepository>()
    }
actual val tokenStorageModule: Module
    get() = module {}

actual val tokenRepositoryModule: Module
    get() = module {
        singleOf(::TokenRepositoryImpl).bind<TokenRepository>()
    }