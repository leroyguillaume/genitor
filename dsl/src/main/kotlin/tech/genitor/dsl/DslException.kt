package tech.genitor.dsl

/**
 * Exception thrown when DSL is invalid.
 */
abstract class DslException : RuntimeException() {
    abstract override val message: String
}
