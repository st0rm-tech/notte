package com.notte.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.notte.app.data.local.dao.NoteDao
import com.notte.app.data.local.entity.NoteEntity

/**
 * Room database for the Notte application.
 * Contains the notes table.
 */
@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NotteDatabase : RoomDatabase() {
    /**
     * Provides access to note data operations.
     */
    abstract fun noteDao(): NoteDao
}
