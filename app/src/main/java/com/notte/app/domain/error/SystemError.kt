package com.notte.app.domain.error

/**
 * Sealed class representing system-level errors.
 */
sealed class SystemError : Exception() {
    object OutOfMemory : SystemError()
    data class UnexpectedCrash(val stackTrace: String) : SystemError()
    data class ConcurrencyConflict(val details: String) : SystemError()
}
