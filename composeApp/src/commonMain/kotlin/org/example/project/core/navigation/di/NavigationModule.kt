package org.example.project.core.navigation.di

import org.example.project.core.navigation.NavigationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val navigationModule = module {
    viewModelOf(::NavigationViewModel)
}