package tech.genitor.core

/**
 * Exception thrown when resource instantiation failed.
 *
 * @param message Error message.
 * @param cause Cause of error.
 */
class ResourceInstantiationException(
    override val message: String,
    override val cause: Throwable? = null
) : RuntimeException()
