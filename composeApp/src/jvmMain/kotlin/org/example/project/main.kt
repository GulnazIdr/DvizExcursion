package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.project.navigation.NavigationGraph
import org.example.project.theme.RedditTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject2",
    ) {
        RedditTheme {
            NavigationGraph()
        }
    }
}