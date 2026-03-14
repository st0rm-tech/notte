package com.notte.app.domain.error

/**
 * Sealed class representing storage-related errors.
 */
sealed class StorageError : Exception() {
    data class InsufficientSpace(val requiredBytes: Long) : StorageError()
    data class DatabaseCorruption(val details: String) : StorageError()
    data class WriteFailure(val noteId: String, override val cause: Throwable) : StorageError()
    data class ReadFailure(val noteId: String, override val cause: Throwable) : StorageError()
}
