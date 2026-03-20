package org.example.project.core.database.di

import org.example.project.core.database.impl.LocalCourseRepositoryImpl
import org.example.project.core.database.impl.LocalUserRepositoryImpl
import org.example.project.core.database.StepikDatabase
import org.example.project.core.database.dao.CourseDao
import org.example.project.core.database.dao.UserDao
import org.example.project.core.database.source.LocalCourseRepository
import org.example.project.core.network.ktor.source.RemoteCourseRepository
import org.example.project.core.network.ktor.repository.RemoteCourseRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val databaseModule: Module

val daoModule = module {
    single{
        val stepikDb: StepikDatabase = get()
        stepikDb.getCourseDao()
    }.bind<CourseDao>()

    single{
        val stepikDb: StepikDatabase = get()
        stepikDb.getUserDao()
    }.bind<UserDao>()
}

val courseRepositoryModule = module{
    singleOf(::RemoteCourseRepositoryImpl).bind<RemoteCourseRepository>()
    singleOf(::LocalCourseRepositoryImpl).bind<LocalCourseRepository>()
}

val userRepositoryModule = module {
    singleOf(::LocalUserRepositoryImpl)
}