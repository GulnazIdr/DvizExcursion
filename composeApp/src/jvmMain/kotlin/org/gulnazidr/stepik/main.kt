package org.gulnazidr.stepik

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.gulnazidr.stepik.core.common.di.initKoin
import org.gulnazidr.stepik.core.navigation.NavigationGraph
import org.gulnazidr.stepik.core.designsystem.theme.StepikTheme
import org.koin.dsl.module

fun main() {
    initKoin(platform = module{})
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KotlinProject2",
        ) {
            StepikTheme {
                NavigationGraph()
            }
        }
    }
}