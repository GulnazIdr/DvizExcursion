package org.gulnazidr.dviz_excursion.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorTheme = lightColorScheme(
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = SuperDarkPurple,
    primaryContainer = LightPurple,
    secondaryContainer = LighterPurple,
    outline = LighterOrange,
    outlineVariant = LightGrey,
    onSecondary = DescrGrey,
    onPrimary = DarkPurple,
    primary = Primary
)

val DarkColorTheme = darkColorScheme(
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    onSurfaceVariant = SuperDarkPurple,
    primaryContainer = LightPurple,
    secondaryContainer = LighterPurple,
    outline = LighterOrange,
    outlineVariant = LightGrey,
    onSecondary = DescrGrey,
    onPrimary = DarkPurple,
    primary = Primary,
    error = Error
)

@Composable
fun MyTheme(
    content: @Composable () -> Unit
){
    val theme = if(isSystemInDarkTheme()) DarkColorTheme else LightColorTheme
    MaterialTheme(
        content = content,
        colorScheme = theme
    )
}