package com.notte.app.presentation.notelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notte.app.domain.model.Note
import com.notte.app.domain.usecase.DeleteNoteUseCase
import com.notte.app.domain.usecase.GetAllNotesUseCase
import com.notte.app.domain.usecase.SearchNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the note list screen.
 * Manages UI state and handles user actions.
 */
@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val searchNotesUseCase: SearchNotesUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<NoteListUiState>(NoteListUiState.Loading)
    val uiState: StateFlow<NoteListUiState> = _uiState.asStateFlow()
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    init {
        observeNotes()
    }
    
    private fun observeNotes() {
        viewModelScope.launch {
            searchQuery.flatMapLatest { query ->
                if (query.isEmpty()) {
                    getAllNotesUseCase()
                } else {
                    searchNotesUseCase(query)
                }
            }.collect { notes ->
                _uiState.value = if (notes.isEmpty()) {
                    NoteListUiState.Empty
                } else {
                    NoteListUiState.Success(notes)
                }
            }
        }
    }
    
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
    
    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note).onFailure { error ->
                _uiState.value = NoteListUiState.Error(
                    error.message ?: "Silme başarısız oldu"
                )
            }
        }
    }
}

/**
 * UI state for the note list screen.
 */
sealed class NoteListUiState {
    object Loading : NoteListUiState()
    object Empty : NoteListUiState()
    data class Success(val notes: List<Note>) : NoteListUiState()
    data class Error(val message: String) : NoteListUiState()
}
