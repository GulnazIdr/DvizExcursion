package org.gulnazidr.stepik.feature.search.di

import org.gulnazidr.stepik.feature.search.domain.SearchCourseUseCase
import org.gulnazidr.stepik.feature.search.presentation.SearchViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val searchModule = module {
    viewModelOf(::SearchViewModel)
    factoryOf(::SearchCourseUseCase)
}