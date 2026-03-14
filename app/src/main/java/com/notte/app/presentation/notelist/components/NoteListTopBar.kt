package com.notte.app.presentation.notelist.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Top bar for note list with search functionality.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListTopBar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            if (searchQuery.isEmpty()) {
                Text("Notte")
            } else {
                TextField(
                    value = searchQuery,
                    onValueChange = onSearchQueryChanged,
                    placeholder = { Text("Notlarda ara...") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Ara")
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { onSearchQueryChanged("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Temizle")
                            }
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        actions = {
            if (searchQuery.isEmpty()) {
                IconButton(onClick = { onSearchQueryChanged(" ") }) {
                    Icon(Icons.Default.Search, contentDescription = "Ara")
                }
            }
        },
        modifier = modifier
    )
}
