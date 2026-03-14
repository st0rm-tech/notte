package com.notte.app.di

import android.content.Context
import androidx.room.Room
import com.notte.app.data.local.dao.NoteDao
import com.notte.app.data.local.database.NotteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module providing database dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideNotteDatabase(
        @ApplicationContext context: Context
    ): NotteDatabase {
        return Room.databaseBuilder(
            context,
            NotteDatabase::class.java,
            "notte_database"
        ).build()
    }
    
    @Provides
    @Singleton
    fun provideNoteDao(database: NotteDatabase): NoteDao {
        return database.noteDao()
    }
}
