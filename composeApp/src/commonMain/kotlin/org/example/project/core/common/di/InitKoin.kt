package org.example.project.core.common.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(courseModule, loginModule, registerModule, httpClientModule)
    }
}