package com.notte.app.domain.usecase

import com.notte.app.domain.model.Note
import com.notte.app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Use case for searching notes.
 * Returns matching notes sorted in reverse chronological order.
 */
class SearchNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    /**
     * Searches notes by query string.
     * 
     * @param query The search query
     * @return Flow emitting sorted list of matching notes
     */
    operator fun invoke(query: String): Flow<List<Note>> {
        return repository.searchNotes(query)
            .map { notes -> notes.sortedByDescending { it.updatedAt } }
    }
}
