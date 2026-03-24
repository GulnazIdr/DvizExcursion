package org.example.project.feature.course_catalog.di

import org.example.project.core.designsystem.ui_logic.mapper.CourseDetailToCourseDetailUiMapper
import org.example.project.core.designsystem.ui_logic.mapper.CourseToCourseDetailUiMapper
import org.example.project.feature.course_catalog.presentation.CourseViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val courseModule = module {
    viewModel {
        CourseViewModel(
            fetchCoursesUseCase = get(),
            courseToCourseDetailUiMapper = get<CourseToCourseDetailUiMapper>(),
            courseDetailUi = get<CourseDetailToCourseDetailUiMapper>()
        )
    }
}