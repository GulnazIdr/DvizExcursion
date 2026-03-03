package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.di.initKoin
import org.example.project.presentation.navigation.NavigationGraph
import org.example.project.presentation.theme.RedditTheme

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KotlinProject2",
        ) {
            RedditTheme {
                NavigationGraph()
            }
        }
    }
}