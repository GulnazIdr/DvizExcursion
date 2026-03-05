package org.gulnazidr.dviz_excursion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.example.project.presentation.navigation.NavigationGraph
import org.example.project.presentation.theme.RedditTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            RedditTheme {
                NavigationGraph()
            }
        }
    }
}