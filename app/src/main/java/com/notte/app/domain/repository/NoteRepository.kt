package com.notte.app.domain.repository

import com.notte.app.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for note data operations.
 * Defines the contract for data access without exposing implementation details.
 */
interface NoteRepository {
    /**
     * Retrieves all notes as a Flow.
     * 
     * @return Flow emitting list of all notes
     */
    fun getAllNotes(): Flow<List<Note>>
    
    /**
     * Retrieves a specific note by its ID.
     * 
     * @param id The unique identifier of the note
     * @return Flow emitting the note if found, null otherwise
     */
    fun getNoteById(id: String): Flow<Note?>
    
    /**
     * Inserts a new note into the repository.
     * 
     * @param note The note to insert
     */
    suspend fun insertNote(note: Note)
    
    /**
     * Updates an existing note in the repository.
     * 
     * @param note The note with updated data
     */
    suspend fun updateNote(note: Note)
    
    /**
     * Deletes a note from the repository.
     * 
     * @param note The note to delete
     */
    suspend fun deleteNote(note: Note)
    
    /**
     * Searches notes by query string.
     * Matches against both title and content.
     * 
     * @param query The search query
     * @return Flow emitting list of matching notes
     */
    fun searchNotes(query: String): Flow<List<Note>>
}
