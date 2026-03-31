package org.gulnazidr.stepik.feature.auth.di

import com.liftric.kvault.KVault
import org.gulnazidr.stepik.feature.auth.data.TokenDataRepositoryImpl
import org.gulnazidr.stepik.feature.auth.domain.token.TokenRepositoryImpl
import org.gulnazidr.stepik.core.domain.auth.TokenDataRepository
import org.gulnazidr.stepik.core.domain.auth.TokenRepository
import org.gulnazidr.stepik.feature.auth.presentation.AuthViewModel
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

actual val authModule: Module
    get() = module {
        viewModelOf(::AuthViewModel)
    }
