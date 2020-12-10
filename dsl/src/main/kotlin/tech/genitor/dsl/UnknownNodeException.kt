package tech.genitor.dsl

/**
 * Exception thrown when try to reference in group a node which is not present in catalog.
 *
 * @param hostname Node hostname.
 */
class UnknownNodeException(
    private val hostname: String
) : DslException() {
    override val message = "Node '$hostname' does not exist!"
}
