package org.example.project.core.common.di

import org.example.project.core.designsystem.ui_logic.mapper.CourseDetailToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.CourseToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.CourseUiMapper
import org.example.project.core.domain.FetchCoursesUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val courseUseCaseModule = module {
    singleOf(::FetchCoursesUseCase)
}
val courseMapperModule = module {
    factory { CourseUiMapper() }
    factory { CourseToCourseDetailUiMapper() }
    factory { CourseDetailToCourseDetailUiMapper(get()) }
}