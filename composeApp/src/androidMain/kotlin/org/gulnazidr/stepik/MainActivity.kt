package org.gulnazidr.stepik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.gulnazidr.stepik.core.designsystem.theme.StepikTheme
import org.gulnazidr.stepik.core.navigation.NavigationGraph

class MainActivity : ComponentActivity() {
//    private val authService: AuthorizationService by lazy { AuthorizationService(this) }
//    private val appAuthHandler by lazy { AppAuthHandler(this, authService) }
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
//
//        initKoin(platform = platformModule(appAuthHandler)) {
//            androidContext(this@MainActivity)
//        }
//
//        appAuthHandler.init()

        setContent {
            StepikTheme {
                NavigationGraph()
            }
        }
    }
}