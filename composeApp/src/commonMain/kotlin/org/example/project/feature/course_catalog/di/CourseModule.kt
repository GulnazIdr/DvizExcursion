package org.example.project.feature.course_catalog.di

import org.example.project.feature.course_catalog.presentation.CourseViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val courseModule = module {
    viewModelOf(::CourseViewModel)
}