package com.notte.app.domain.usecase

import com.notte.app.domain.model.Note
import com.notte.app.domain.repository.NoteRepository
import javax.inject.Inject

/**
 * Use case for creating a new note.
 * Validates content before creation.
 */
class CreateNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    /**
     * Creates a new note with the given content.
     * 
     * @param content The note content
     * @return Result containing the created note or an error
     */
    suspend operator fun invoke(content: String): Result<Note> {
        if (content.isBlank()) {
            return Result.failure(InvalidNoteException("İçerik boş olamaz"))
        }
        
        val note = Note(
            content = content,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        
        return try {
            repository.insertNote(note)
            Result.success(note)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

/**
 * Exception thrown when note validation fails.
 */
class InvalidNoteException(message: String) : Exception(message)
