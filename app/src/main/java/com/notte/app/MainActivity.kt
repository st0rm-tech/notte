package com.notte.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.notte.app.presentation.navigation.NotteNavigation
import com.notte.app.presentation.theme.NotteTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity for Notte application.
 * Entry point for the app with Hilt dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            NotteTheme {
                NotteNavigation()
            }
        }
    }
}
