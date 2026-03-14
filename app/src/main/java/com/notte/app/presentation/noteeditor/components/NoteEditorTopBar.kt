package com.notte.app.presentation.noteeditor.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Top bar for note editor with back navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorTopBar(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text("") },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Geri"
                )
            }
        },
        modifier = modifier
    )
}
