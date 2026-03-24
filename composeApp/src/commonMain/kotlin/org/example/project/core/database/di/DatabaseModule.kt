package org.example.project.core.database.di

import org.example.project.core.database.impl.LocalCourseRepositoryImpl
import org.example.project.core.database.impl.LocalUserRepositoryImpl
import org.example.project.core.database.StepikDatabase
import org.example.project.core.database.dao.CourseDao
import org.example.project.core.database.source.LocalCourseRepository
import org.example.project.core.database.source.LocalUserRepository
import org.example.project.core.network.ktor.course.source.RemoteCourseRepository
import org.example.project.core.network.ktor.course.repository.RemoteCourseRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val databaseModule: Module

val daoModule = module {
    single{
        val stepikDb: StepikDatabase = get()
        stepikDb.getCourseDao()
    }.bind<CourseDao>()
}

val courseRepositoryModule = module {
    factory<RemoteCourseRepository> { RemoteCourseRepositoryImpl(get(), get()) }
    factory<LocalCourseRepository> { LocalCourseRepositoryImpl(get()) }
}

val userRepositoryModule = module {
    factory<LocalUserRepository>{ LocalUserRepositoryImpl(get()) }
}