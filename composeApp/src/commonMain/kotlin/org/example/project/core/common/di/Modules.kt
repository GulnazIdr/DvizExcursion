package org.example.project.core.common.di

import org.example.project.core.designsystem.ui_logic.mapper.CourseDetailToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.CourseToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.CourseUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.UserToUserUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.UserUiToUserMapper
import org.example.project.core.domain.courses.FetchAuthorsUseCase
import org.example.project.core.domain.courses.FetchCoursesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val courseUseCaseModule = module {
    singleOf(::FetchCoursesUseCase)
    factoryOf(::FetchAuthorsUseCase)
}
val courseMapperModule = module {
    factory { ::CourseUiMapper }
    factory { ::CourseToCourseDetailUiMapper }
    factory { ::CourseDetailToCourseDetailUiMapper }
}

val userMapperModule = module {
    factory { ::UserUiToUserMapper }
    factory { ::UserToUserUiMapper }
}