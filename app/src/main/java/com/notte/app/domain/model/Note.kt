package com.notte.app.domain.model

import java.util.UUID

/**
 * Domain model representing a note.
 * 
 * @property id Unique identifier for the note
 * @property content The text content of the note
 * @property createdAt Timestamp when the note was created (milliseconds since epoch)
 * @property updatedAt Timestamp when the note was last updated (milliseconds since epoch)
 */
data class Note(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    /**
     * Checks if the note has valid content (not blank).
     * 
     * @return true if content is not blank, false otherwise
     */
    fun isValid(): Boolean = content.isNotBlank()
    
    /**
     * Extracts the title from the note content.
     * Uses the first line as title, or first 60 characters if no line breaks exist.
     * 
     * @return The note title
     */
    fun getTitle(): String {
        val firstLine = content.lines().firstOrNull() ?: ""
        return firstLine.ifEmpty { content.take(60) }
    }
    
    /**
     * Extracts a preview from the note content.
     * Uses the second line if available, otherwise first 60 characters.
     * 
     * @return The note preview text
     */
    fun getPreview(): String {
        val lines = content.lines()
        return if (lines.size > 1) {
            lines[1].take(60)
        } else {
            content.take(60)
        }
    }
}
