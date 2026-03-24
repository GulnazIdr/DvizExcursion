package org.example.project.core.common.di

import org.example.project.core.database.di.courseRepositoryModule
import org.example.project.core.database.di.daoModule
import org.example.project.core.database.di.databaseModule
import org.example.project.core.database.di.userRepositoryModule
import org.example.project.core.datastore.dataStoreModule
import org.example.project.core.datastore.dataStoreRepositoryModule
import org.example.project.core.navigation.di.navigationModule
import org.example.project.feature.auth.di.loginModule
import org.example.project.feature.auth.di.registerModule
import org.example.project.feature.auth.di.secureTokenStorageModule
import org.example.project.feature.auth.di.tokenDataRepositoryModule
import org.example.project.feature.auth.di.tokenRepositoryModule
import org.example.project.feature.auth.di.tokenStorageModule
import org.example.project.feature.profile.di.profileModule
import org.example.project.feature.profile.di.userMapperModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

fun initKoin(platform: Module, config: KoinAppDeclaration? = null, ){
    startKoin {
        config?.invoke(this)
        modules(
             loginModule, registerModule, secureTokenStorageModule, tokenDataRepositoryModule,
            tokenStorageModule, httpClientModule, searchModule, courseDetailModule, authModule,
            databaseModule, daoModule, courseRepositoryModule, courseUseCaseModule, profileModule,
            userRepositoryModule, dataStoreModule, dataStoreRepositoryModule, navigationModule,
            courseMapperModule, userMapperModule, platform, courseModule, tokenRepositoryModule
        )
    }
}