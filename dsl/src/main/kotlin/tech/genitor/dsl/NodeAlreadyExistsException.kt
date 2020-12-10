package tech.genitor.dsl

/**
 * Exception thrown when try to create a node with the same hostname from another one.
 *
 * @param hostname Node hostname.
 */
class NodeAlreadyExistsException(
    private val hostname: String
) : DslException() {
    override val message = "Node '$hostname' already exists!"
}
