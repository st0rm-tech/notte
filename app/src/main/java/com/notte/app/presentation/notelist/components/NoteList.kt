package com.notte.app.presentation.notelist.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.notte.app.domain.model.Note

/**
 * Grid list of notes with responsive layout.
 * Shows 1 column on narrow screens, 2 columns on wider screens.
 */
@Composable
fun NoteList(
    notes: List<Note>,
    onNoteClick: (String) -> Unit,
    onNoteLongPress: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val columns = if (configuration.screenWidthDp >= 600) 2 else 1
    
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(
            items = notes,
            key = { it.id },
            contentType = { "note_card" }
        ) { note ->
            NoteCard(
                note = note,
                onClick = { onNoteClick(note.id) },
                onLongPress = { onNoteLongPress(note) },
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}
