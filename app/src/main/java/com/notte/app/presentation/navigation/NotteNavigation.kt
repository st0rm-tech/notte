package com.notte.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.notte.app.presentation.animation.NotteAnimations
import com.notte.app.presentation.noteeditor.NoteEditorScreen
import com.notte.app.presentation.notelist.NoteListScreen

/**
 * Main navigation component for Notte.
 * Handles navigation between note list and editor screens.
 */
@Composable
fun NotteNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "note_list",
        enterTransition = { NotteAnimations.navigationEnterTransition },
        exitTransition = { NotteAnimations.navigationExitTransition },
        popEnterTransition = { NotteAnimations.navigationPopEnterTransition },
        popExitTransition = { NotteAnimations.navigationPopExitTransition }
    ) {
        composable("note_list") {
            NoteListScreen(
                onNoteClick = { noteId ->
                    navController.navigate("note_editor/$noteId")
                },
                onCreateClick = {
                    navController.navigate("note_editor/new")
                }
            )
        }
        
        composable(
            route = "note_editor/{noteId}",
            arguments = listOf(
                navArgument("noteId") { type = NavType.StringType }
            )
        ) {
            NoteEditorScreen(
                onBackPressed = { navController.popBackStack() }
            )
        }
    }
}
