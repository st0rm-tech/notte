package com.notte.app.data.local.dao

import androidx.room.*
import com.notte.app.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for note operations.
 * Provides methods for CRUD operations and search.
 */
@Dao
interface NoteDao {
    /**
     * Retrieves all notes ordered by update time (descending).
     */
    @Query("SELECT * FROM notes ORDER BY updated_at DESC")
    fun getAllNotes(): Flow<List<NoteEntity>>
    
    /**
     * Retrieves a specific note by ID.
     */
    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: String): Flow<NoteEntity?>
    
    /**
     * Inserts a note, replacing if it already exists.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)
    
    /**
     * Updates an existing note.
     */
    @Update
    suspend fun updateNote(note: NoteEntity)
    
    /**
     * Deletes a note.
     */
    @Delete
    suspend fun deleteNote(note: NoteEntity)
    
    /**
     * Searches notes by content, ordered by update time (descending).
     */
    @Query("SELECT * FROM notes WHERE content LIKE '%' || :query || '%' ORDER BY updated_at DESC")
    fun searchNotes(query: String): Flow<List<NoteEntity>>
}
