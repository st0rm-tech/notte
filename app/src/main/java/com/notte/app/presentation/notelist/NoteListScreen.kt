package com.notte.app.presentation.notelist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.notte.app.presentation.notelist.components.*

/**
 * Main screen displaying the list of notes.
 */
@Composable
fun NoteListScreen(
    onNoteClick: (String) -> Unit,
    onCreateClick: () -> Unit,
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    
    Scaffold(
        topBar = {
            NoteListTopBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = viewModel::onSearchQueryChanged
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreateClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Not oluştur"
                )
            }
        }
    ) { padding ->
        when (val state = uiState) {
            is NoteListUiState.Loading -> {
                LoadingIndicator(modifier = Modifier.padding(padding))
            }
            is NoteListUiState.Empty -> {
                EmptyState(
                    onCreateClick = onCreateClick,
                    modifier = Modifier.padding(padding)
                )
            }
            is NoteListUiState.Success -> {
                NoteList(
                    notes = state.notes,
                    onNoteClick = onNoteClick,
                    onNoteLongPress = viewModel::deleteNote,
                    modifier = Modifier.padding(padding)
                )
            }
            is NoteListUiState.Error -> {
                ErrorState(
                    message = state.message,
                    modifier = Modifier.padding(padding)
                )
            }
        }
    }
}

@Composable
private fun ErrorState(
    message: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = message,
        modifier = modifier,
        color = MaterialTheme.colorScheme.error
    )
}
