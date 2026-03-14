package com.notte.app.domain.usecase

import com.notte.app.domain.model.Note
import com.notte.app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case for retrieving all notes.
 * Returns notes sorted in reverse chronological order (most recent first).
 */
class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    /**
     * Retrieves all notes sorted by update time (descending).
     * 
     * @return Flow emitting sorted list of notes
     */
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAllNotes()
            .map { notes -> notes.sortedByDescending { it.updatedAt } }
    }
}
