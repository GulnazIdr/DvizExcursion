package org.example.project.feature.auth.di

import androidx.core.net.toUri
import com.liftric.kvault.KVault
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.example.project.feature.auth.data.TokenDataRepositoryImpl
import org.example.project.feature.auth.domain.AuthConfig
import org.example.project.feature.auth.domain.TokenRepositoryImpl
import org.example.project.feature.auth.domain.token.TokenRepository
import org.example.project.feature.auth.presentation.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
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


actual val tokenRepositoryModule: Module
    get() = module {
        singleOf(::TokenRepositoryImpl).bind<TokenRepository>()
    }

actual val authModule: Module
    get() = module {
        viewModelOf(::AuthViewModel)
    }
