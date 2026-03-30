package org.gulnazidr.stepik.core.network.ktor.course.di

import org.gulnazidr.stepik.core.network.ktor.course.KtorCourseRepository
import org.gulnazidr.stepik.core.network.ktor.course.KtorCourseRepositoryImpl
import org.koin.dsl.module

val ktorCourseRepositoryModule = module {
    factory<KtorCourseRepository> {
        KtorCourseRepositoryImpl(
            get(),
            get(),
            get(),
        )
    }
}