package org.gulnazidr.dviz_excursion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import org.gulnazidr.dviz_excursion.presentation.navigation.NavigationGraph
import org.gulnazidr.dviz_excursion.presentation.theme.MyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                NavigationGraph()
            }
        }
    }
}