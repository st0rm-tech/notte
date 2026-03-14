package com.notte.app.domain.usecase

import com.notte.app.domain.model.Note
import com.notte.app.domain.repository.NoteRepository
import javax.inject.Inject

/**
 * Use case for deleting a note.
 */
class DeleteNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    /**
     * Deletes the specified note.
     * 
     * @param note The note to delete
     * @return Result indicating success or failure
     */
    suspend operator fun invoke(note: Note): Result<Unit> {
        return try {
            repository.deleteNote(note)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
