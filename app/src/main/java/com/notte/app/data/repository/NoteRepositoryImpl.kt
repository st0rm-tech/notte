package com.notte.app.data.repository

import com.notte.app.data.local.dao.NoteDao
import com.notte.app.data.mapper.toDomain
import com.notte.app.data.mapper.toEntity
import com.notte.app.di.IoDispatcher
import com.notte.app.domain.model.Note
import com.notte.app.domain.repository.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of NoteRepository.
 * Handles data operations using Room database with IO dispatcher.
 */
class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NoteRepository {
    
    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getNoteById(id: String): Flow<Note?> {
        return noteDao.getNoteById(id).map { it?.toDomain() }
    }
    
    override suspend fun insertNote(note: Note) = withContext(ioDispatcher) {
        noteDao.insertNote(note.toEntity())
    }
    
    override suspend fun updateNote(note: Note) = withContext(ioDispatcher) {
        noteDao.updateNote(note.toEntity())
    }
    
    override suspend fun deleteNote(note: Note) = withContext(ioDispatcher) {
        noteDao.deleteNote(note.toEntity())
    }
    
    override fun searchNotes(query: String): Flow<List<Note>> {
        return noteDao.searchNotes(query).map { entities ->
            entities.map { it.toDomain() }
        }
    }
}
