package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.core.common.di.initKoin
import org.example.project.core.navigation.NavigationGraph
import org.example.project.core.designsystem.theme.StepikTheme
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