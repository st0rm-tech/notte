package com.notte.app.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = NotteColors.Primary,
    secondary = NotteColors.Secondary,
    tertiary = NotteColors.Tertiary,
    surface = NotteColors.LightSurface,
    background = NotteColors.Background,
    onSurface = NotteColors.LightOnSurface,
    onSurfaceVariant = NotteColors.LightOnSurfaceVariant
)

private val DarkColorScheme = darkColorScheme(
    primary = NotteColors.DarkPrimary,
    secondary = NotteColors.DarkSecondary,
    tertiary = NotteColors.DarkTertiary,
    surface = NotteColors.DarkSurface,
    onSurface = NotteColors.DarkOnSurface,
    onSurfaceVariant = NotteColors.DarkOnSurfaceVariant
)

/**
 * Notte theme composable.
 * Provides Material Design 3 theming with macOS-inspired colors.
 */
@Composable
fun NotteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = NotteTypography,
        shapes = NotteShapes,
        content = content
    )
}
