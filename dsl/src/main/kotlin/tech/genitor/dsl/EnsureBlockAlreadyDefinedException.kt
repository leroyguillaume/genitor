package tech.genitor.dsl

/**
 * Exception thrown when ensure function is called twice.
 *
 * @param message Error message.
 */
class EnsureBlockAlreadyDefinedException(
    override val message: String
) : DslException()
