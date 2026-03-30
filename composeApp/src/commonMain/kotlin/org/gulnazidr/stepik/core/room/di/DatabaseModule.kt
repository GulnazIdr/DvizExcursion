package org.gulnazidr.stepik.core.room.di

import org.gulnazidr.stepik.core.room.StepikDatabase
import org.gulnazidr.stepik.core.room.dao.CourseDao
import org.gulnazidr.stepik.core.room.impl.RoomCourseRepositoryImpl
import org.gulnazidr.stepik.core.room.source.RoomCourseRepository
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

val roomCourseRepositoryModule = module {
    factory<RoomCourseRepository> { RoomCourseRepositoryImpl(get()) }
}