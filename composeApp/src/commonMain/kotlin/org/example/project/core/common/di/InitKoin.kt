package org.example.project.core.common.di

import org.example.project.core.database.di.courseRepositoryModule
import org.example.project.core.database.di.daoModule
import org.example.project.core.database.di.databaseModule
import org.example.project.core.database.di.userRepositoryModule
import org.example.project.core.datastore.dataStoreModule
import org.example.project.core.datastore.dataStoreRepositoryModule
import org.example.project.core.navigation.di.navigationModule
import org.example.project.feature.profile.di.profileModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
            courseModule, loginModule, registerModule, httpClientModule,
            searchModule, courseDetailModule, authModule, databaseModule, daoModule,
            courseRepositoryModule, courseUseCaseModule, profileModule, userRepositoryModule,
            dataStoreModule, dataStoreRepositoryModule, navigationModule
        )
    }
}