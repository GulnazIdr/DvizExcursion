package org.gulnazidr.stepik.feature.course_catalog.di

import org.gulnazidr.stepik.core.common.di.UserSessionScope
import org.gulnazidr.stepik.core.domain.courses.source.CourseRepository
import org.gulnazidr.stepik.feature.course_catalog.domain.CourseRepositoryImpl
import org.gulnazidr.stepik.feature.course_catalog.domain.FetchAuthorsUseCase
import org.gulnazidr.stepik.feature.course_catalog.domain.FetchCoursesUseCase
import org.gulnazidr.stepik.feature.course_catalog.domain.local.LocalCourseRepository
import org.gulnazidr.stepik.feature.course_catalog.domain.local.LocalCourseRepositoryImpl
import org.gulnazidr.stepik.feature.course_catalog.domain.remote.RemoteCourseRepository
import org.gulnazidr.stepik.feature.course_catalog.domain.remote.RemoteCourseRepositoryImpl
import org.gulnazidr.stepik.feature.course_catalog.presentation.CourseViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val courseModule = module {
    viewModel { (fetchUseCase: FetchCoursesUseCase) ->
        CourseViewModel(
            fetchCoursesUseCase = fetchUseCase,
            courseToCourseDetailUiMapper = get(),
            fetchAuthorsUseCase = get(),
            userUiMapper = get(),
            logoutUseCase = get()
        )
    }
}

val courseUseCaseModule = module {
    scope(UserSessionScope.USER_SESSION_SCOPE) {
        scoped { FetchCoursesUseCase(get()) }
    }
    factoryOf(::FetchAuthorsUseCase)
}

val courseRepositoryModule = module {
    factory<RemoteCourseRepository> { RemoteCourseRepositoryImpl(get()) }
    factory<LocalCourseRepository> { LocalCourseRepositoryImpl(get()) }
    factory<CourseRepository> {
        CourseRepositoryImpl(
            get(),
            get(),
        )
    }
}