package com.notte.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Room database entity for notes.
 * Includes indices for optimized querying.
 */
@Entity(
    tableName = "notes",
    indices = [
        Index(value = ["updated_at"], name = "idx_updated_at"),
        Index(value = ["content"], name = "idx_content")
    ]
)
data class NoteEntity(
    @PrimaryKey
    val id: String,
    
    @ColumnInfo(name = "content")
    val content: String,
    
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long
)
