package org.gulnazidr.stepik.core.navigation.di

import org.gulnazidr.stepik.core.navigation.NavigationViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val navigationModule = module {
    viewModelOf(::NavigationViewModel)
}