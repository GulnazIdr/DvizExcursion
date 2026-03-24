package org.example.project.core.network.ktor.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val httpClientModule = module {
    single<HttpClientEngine>{
        OkHttp.create()
    }

    single<HttpClient>{
        HttpClient(get<HttpClientEngine>()){
            expectSuccess = false

            install(Logging){
                level = LogLevel.ALL
            }

            followRedirects = true
            install(ContentNegotiation){
                json(
                    json = Json{
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
}
