package org.example.project.feature.course_detail.di

import org.example.project.feature.course_detail.domain.FetchCourseDetailUseCase
import org.example.project.feature.course_detail.presentation.CourseDetailViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val courseDetailModule = module {
    viewModel { (courseId: Int) ->
        CourseDetailViewModel(
            courseId = courseId,
            fetchCourseDetailUseCase = get(),
            courseDetailToCourseDetailUiMapper = get()
        )
    }
    factoryOf(::FetchCourseDetailUseCase)
}
