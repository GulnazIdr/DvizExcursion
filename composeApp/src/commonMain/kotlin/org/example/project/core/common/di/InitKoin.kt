package org.example.project.core.common.di

import org.example.project.core.database.di.courseRepositoryModule
import org.example.project.core.database.di.daoModule
import org.example.project.core.database.di.databaseModule
import org.example.project.core.database.di.userRepositoryModule
import org.example.project.core.datastore.di.dataStoreModule
import org.example.project.core.datastore.di.dataStoreRepositoryModule
import org.example.project.core.navigation.di.navigationModule
import org.example.project.core.network.ktor.di.httpClientModule
import org.example.project.feature.auth.di.authModule
import org.example.project.feature.auth.di.loginModule
import org.example.project.feature.auth.di.registerModule
import org.example.project.feature.auth.di.secureTokenStorageModule
import org.example.project.feature.auth.di.tokenDataRepositoryModule
import org.example.project.feature.auth.di.tokenRepositoryModule
import org.example.project.feature.course_catalog.di.courseModule
import org.example.project.feature.course_detail.di.courseDetailModule
import org.example.project.feature.profile.di.profileModule
import org.example.project.feature.profile.di.userMapperModule
import org.example.project.feature.search.di.searchModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(platform: Module, config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
             loginModule, registerModule, secureTokenStorageModule, tokenDataRepositoryModule,
            httpClientModule, searchModule, courseDetailModule, authModule,
            databaseModule, daoModule, courseRepositoryModule, courseUseCaseModule, profileModule,
            userRepositoryModule, dataStoreModule, dataStoreRepositoryModule, navigationModule,
            courseMapperModule, userMapperModule, platform, courseModule, tokenRepositoryModule
        )
    }
}