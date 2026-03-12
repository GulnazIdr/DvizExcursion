package org.example.project.core.common.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.core.network.ktor.StepikApiImpl
import org.example.project.feature.auth.data.LoginRepositoryImpl
import org.example.project.feature.auth.data.RegisterRepositoryImpl
import org.example.project.feature.auth.domain.login.LoginRepository
import org.example.project.feature.auth.domain.login.LoginUseCase
import org.example.project.feature.auth.domain.registration.RegisterRepository
import org.example.project.feature.auth.presentation.login.LoginViewModel
import org.example.project.feature.auth.presentation.register.RegistrationViewmodel
import org.example.project.feature.main.domain.StepikApi
import org.example.project.feature.main.presentation.CourseViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val courseModule = module {
    singleOf(::StepikApiImpl).bind<StepikApi>()
    viewModelOf(::CourseViewModel)
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

val httpClientModule = module {
    single<HttpClientEngine>{
        OkHttp.create()
    }

    single<HttpClient>{
        HttpClient(get<HttpClientEngine>()){
            install(Logging){
                level = LogLevel.ALL
            }

            install(ContentNegotiation){
                json(
                    json = Json{
                        explicitNulls = false
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}

expect val dataStoreModule: Module
internal val DATA_STORE_FILE_NAME = "prefs.preferences_"