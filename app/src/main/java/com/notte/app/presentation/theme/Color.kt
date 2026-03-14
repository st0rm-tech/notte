package com.notte.app.presentation.theme

import androidx.compose.ui.graphics.Color

/**
 * macOS-inspired color palette for Notte.
 * Uses green tones as specified in requirements.
 */
object NotteColors {
    // Primary colors (macOS green tones)
    val Primary = Color(0xFF84B179)      // #84B179
    val Secondary = Color(0xFFA2CB8B)    // #A2CB8B
    val Tertiary = Color(0xFFC7EABB)     // #C7EABB
    val Background = Color(0xFFE8F5BD)   // #E8F5BD
    
    // Light mode
    val LightSurface = Color(0xFFFFFBF5)
    val LightOnSurface = Color(0xFF1C1B1F)
    val LightOnSurfaceVariant = Color(0xFF49454F)
    
    // Dark mode
    val DarkSurface = Color(0xFF1C1B1F)
    val DarkOnSurface = Color(0xFFE6E1E5)
    val DarkOnSurfaceVariant = Color(0xFFCAC4D0)
    
    // Dark mode adapted green tones
    val DarkPrimary = Color(0xFF9BC98E)
    val DarkSecondary = Color(0xFFB5D9A3)
    val DarkTertiary = Color(0xFFD4F0C8)
}
