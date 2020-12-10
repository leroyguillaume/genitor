package tech.genitor.dsl

/**
 * Exception thrown when ensure function is called twice.
 */
class EnsureBlockAlreadyDefinedException : DslException() {
    override val message = "Ensure block is already defined!"
}
