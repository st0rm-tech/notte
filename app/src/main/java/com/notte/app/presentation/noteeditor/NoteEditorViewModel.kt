package com.notte.app.presentation.noteeditor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notte.app.domain.model.Note
import com.notte.app.domain.repository.NoteRepository
import com.notte.app.domain.usecase.CreateNoteUseCase
import com.notte.app.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for the note editor screen.
 * Handles note creation, editing, and auto-save functionality.
 */
@HiltViewModel
class NoteEditorViewModel @Inject constructor(
    private val createNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val noteId: String? = savedStateHandle["noteId"]
    
    private val _uiState = MutableStateFlow<NoteEditorUiState>(NoteEditorUiState.Loading)
    val uiState: StateFlow<NoteEditorUiState> = _uiState.asStateFlow()
    
    private val _content = MutableStateFlow("")
    val content: StateFlow<String> = _content.asStateFlow()
    
    private var currentNote: Note? = null
    private var autoSaveJob: Job? = null
    
    init {
        loadNote()
        setupAutoSave()
    }
    
    private fun loadNote() {
        viewModelScope.launch {
            if (noteId != null && noteId != "new") {
                repository.getNoteById(noteId).collect { note ->
                    if (note != null) {
                        currentNote = note
                        _content.value = note.content
                        _uiState.value = NoteEditorUiState.Editing(note)
                    }
                }
            } else {
                _uiState.value = NoteEditorUiState.Creating
            }
        }
    }
    
    private fun setupAutoSave() {
        viewModelScope.launch {
            content
                .debounce(2000) // 2 saniye bekle
                .collect { content ->
                    if (content.isNotBlank()) {
                        saveNote(content)
                    }
                }
        }
    }
    
    fun onContentChanged(newContent: String) {
        _content.value = newContent
    }
    
    private suspend fun saveNote(content: String) {
        if (currentNote != null) {
            updateNoteUseCase(currentNote!!.copy(content = content))
        } else if (content.isNotBlank()) {
            createNoteUseCase(content).onSuccess { note ->
                currentNote = note
            }
        }
    }
    
    fun onBackPressed() {
        viewModelScope.launch {
            val content = _content.value
            if (content.isNotBlank()) {
                saveNote(content)
            }
        }
    }
}

/**
 * UI state for the note editor screen.
 */
sealed class NoteEditorUiState {
    object Loading : NoteEditorUiState()
    object Creating : NoteEditorUiState()
    data class Editing(val note: Note) : NoteEditorUiState()
    data class Error(val message: String) : NoteEditorUiState()
}
