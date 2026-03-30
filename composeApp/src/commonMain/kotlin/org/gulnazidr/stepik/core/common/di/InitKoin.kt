package org.gulnazidr.stepik.core.common.di

import org.gulnazidr.stepik.core.common.di.courseMapperModule
import org.gulnazidr.stepik.core.common.di.userMapperModule
import org.gulnazidr.stepik.core.datastore.di.dataStoreModule
import org.gulnazidr.stepik.core.datastore.di.userDataStoreModule
import org.gulnazidr.stepik.core.datastore.user.di.userDataStoreRepoModule
import org.gulnazidr.stepik.core.navigation.di.navigationModule
import org.gulnazidr.stepik.core.network.di.httpClientModule
import org.gulnazidr.stepik.core.network.di.metaModule
import org.gulnazidr.stepik.core.network.ktor.course.di.ktorCourseRepositoryModule
import org.gulnazidr.stepik.core.network.ktor.user.di.userKtorRepositoryModule
import org.gulnazidr.stepik.core.room.di.daoModule
import org.gulnazidr.stepik.core.room.di.databaseModule
import org.gulnazidr.stepik.core.room.di.roomCourseRepositoryModule
import org.gulnazidr.stepik.feature.auth.di.authModule
import org.gulnazidr.stepik.feature.auth.di.loginModule
import org.gulnazidr.stepik.feature.auth.di.registerModule
import org.gulnazidr.stepik.feature.auth.di.secureTokenStorageModule
import org.gulnazidr.stepik.feature.auth.di.tokenDataRepositoryModule
import org.gulnazidr.stepik.feature.auth.di.tokenRepositoryModule
import org.gulnazidr.stepik.feature.course_catalog.di.courseModule
import org.gulnazidr.stepik.feature.course_catalog.di.courseRepositoryModule
import org.gulnazidr.stepik.feature.course_catalog.di.courseUseCaseModule
import org.gulnazidr.stepik.feature.course_detail.di.courseDetailModule
import org.gulnazidr.stepik.feature.onboarding.di.boardingDataStoreModule
import org.gulnazidr.stepik.feature.profile.di.profileModule
import org.gulnazidr.stepik.feature.profile.di.userModule
import org.gulnazidr.stepik.feature.search.di.searchModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null){
    startKoin {
        config?.invoke(this)
        modules(
             loginModule, registerModule, secureTokenStorageModule, tokenDataRepositoryModule,
            httpClientModule, searchModule, courseDetailModule, authModule,
            databaseModule, daoModule, courseRepositoryModule, courseUseCaseModule, profileModule,
            userKtorRepositoryModule, dataStoreModule, boardingDataStoreModule, navigationModule,
            courseMapperModule, userMapperModule, courseModule, tokenRepositoryModule,
            userDataStoreModule, userDataStoreRepoModule, metaModule, ktorCourseRepositoryModule,
            roomCourseRepositoryModule, userModule
        )
    }
}