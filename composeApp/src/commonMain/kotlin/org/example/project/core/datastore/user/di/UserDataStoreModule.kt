package org.example.project.core.datastore.user.di

import org.example.project.core.datastore.user.impl.UserDataStoreRepositoryImpl
import org.example.project.core.datastore.user.source.UserDataStoreRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val userDataStoreRepoModule = module {
    factory<UserDataStoreRepository> { UserDataStoreRepositoryImpl(
        get(named("data_user")))
    }
}