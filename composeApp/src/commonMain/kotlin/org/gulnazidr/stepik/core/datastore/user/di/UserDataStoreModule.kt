package org.gulnazidr.stepik.core.datastore.user.di

import org.gulnazidr.stepik.core.datastore.user.impl.UserDataStoreRepositoryImpl
import org.gulnazidr.stepik.core.datastore.user.source.UserDataStoreRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val userDataStoreRepoModule = module {
    factory<UserDataStoreRepository> { UserDataStoreRepositoryImpl(
        get(named("data_user")))
    }
}