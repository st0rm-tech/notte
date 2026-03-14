package com.notte.app.domain.error

/**
 * Sealed class representing validation errors.
 */
sealed class ValidationError : Exception() {
    object EmptyContent : ValidationError()
    data class ContentTooLarge(val maxSize: Int) : ValidationError()
    object InvalidNoteId : ValidationError()
}
