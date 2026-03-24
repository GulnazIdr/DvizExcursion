package org.gulnazidr.dviz_excursion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import net.openid.appauth.AuthorizationService
import org.example.project.core.common.di.initKoin
import org.example.project.core.common.di.platformModule
import org.example.project.core.designsystem.theme.StepikTheme
import org.example.project.core.navigation.NavigationGraph
import org.example.project.feature.auth.presentation.AppAuthHandler
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    private val authService: AuthorizationService by lazy { AuthorizationService(this) }
    private val appAuthHandler by lazy { AppAuthHandler(this, authService) }
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initKoin(platform = platformModule(appAuthHandler)) {
            androidContext(this@MainActivity)
        }

        appAuthHandler.init()

        setContent {
            StepikTheme {
                NavigationGraph()
            }
        }
    }
}