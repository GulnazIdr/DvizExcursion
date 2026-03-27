package org.example.project.core.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.core.network.mapper.MetaToPageInfoMapper
import org.example.project.feature.auth.domain.token.TokenDataRepository
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
val httpClientModule = module {
    single<HttpClientEngine>{
        OkHttp.create()
    }
    single<HttpClient>{
        HttpClient(get<HttpClientEngine>()){

            defaultRequest {
                headers{
                    append("Authorization", "Bearer " +
                            "${get<TokenDataRepository>().getAccessToken()}")
                    append(HttpHeaders.ContentType,
                        ContentType.Application.Json.toString())
                }
                url {
                    protocol = URLProtocol.HTTPS
                    host = "stepik.org"
                    path("api/")
                }
            }

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

val metaModule = module {
    factoryOf(::MetaToPageInfoMapper)
}
