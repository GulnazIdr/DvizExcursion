package org.example.project.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val LightColorTheme = lightColorScheme(
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = SuperDarkRed,
    primaryContainer = LightPink,
    secondaryContainer = SecondaryContainer,
    outline = LighterOrange,
    outlineVariant = LightGrey,
    onSecondary = DescrGrey,
    onPrimary = Orange,
    primary = Primary,
    onSecondaryContainer = OnSecondaryContainer,
    secondary = LighterPurple,
    tertiary = Tertiary,
    error = Error,
    tertiaryContainer = LightGreen,
    surfaceContainer = BananaYellow,
    onTertiaryContainer = Dark,
    onBackground = LightBlue,
    onPrimaryFixed = Green,
    onPrimaryContainer = White
)

val DarkColorTheme = darkColorScheme(
    background = BackgroundDark,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = SuperDarkRed,
    primaryContainer = LightPink,
    secondaryContainer = SecondaryContainerDark,
    outline = LighterOrange,
    outlineVariant = DescrGrey,
    onSecondary = DescrGrey,
    onPrimary = Orange,
    primary = Primary,
    onSecondaryContainer = OnSecondaryContainerDark,
    secondary = LighterPurple,
    tertiary = Tertiary,
    error = Error,
    tertiaryContainer = LightGreen,
    surfaceContainer = BananaYellow,
    onTertiaryContainer = Dark,
    onPrimaryFixed = Green,
    onPrimaryContainer = White
)

@Composable
fun RedditTheme(
    content: @Composable () -> Unit
){
    val theme = if(isSystemInDarkTheme()) DarkColorTheme else LightColorTheme
    MaterialTheme(
        content = content,
        colorScheme = theme,
        typography = Typography
    )
}