package org.example.project.feature.auth.di

import com.liftric.kvault.KVault
import org.example.project.feature.auth.data.TokenDataRepositoryImpl
import org.example.project.feature.auth.domain.TokenRepositoryImpl
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.auth.domain.token.TokenRepository
import org.example.project.feature.auth.presentation.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val secureTokenStorageModule: Module
    get() = module {
        single { KVault(androidContext(), "authTokenSecureStorage") }
    }

actual val tokenDataRepositoryModule: Module
    get() = module {
        factory<TokenDataRepository>{TokenDataRepositoryImpl(get())}
    }

actual val tokenRepositoryModule: Module
    get() = module {
        factory<TokenRepository>{TokenRepositoryImpl(get(), get())}
    }

actual val authModule: Module
    get() = module {
        viewModelOf(::AuthViewModel)
    }
