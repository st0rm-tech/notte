package com.notte.app.data.mapper

import com.notte.app.data.local.entity.NoteEntity
import com.notte.app.domain.model.Note

/**
 * Converts NoteEntity to domain Note model.
 */
fun NoteEntity.toDomain(): Note {
    return Note(
        id = id,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

/**
 * Converts domain Note model to NoteEntity.
 */
fun Note.toEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
