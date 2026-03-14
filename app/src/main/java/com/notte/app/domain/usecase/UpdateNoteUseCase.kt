package com.notte.app.domain.usecase

import com.notte.app.domain.model.Note
import com.notte.app.domain.repository.NoteRepository
import javax.inject.Inject

/**
 * Use case for updating an existing note.
 * Updates the timestamp automatically.
 */
class UpdateNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    /**
     * Updates a note with new data.
     * 
     * @param note The note to update
     * @return Result indicating success or failure
     */
    suspend operator fun invoke(note: Note): Result<Unit> {
        if (!note.isValid()) {
            return Result.failure(InvalidNoteException("Not içeriği geçersiz"))
        }
        
        return try {
            val updatedNote = note.copy(updatedAt = System.currentTimeMillis())
            repository.updateNote(updatedNote)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
