package tech.genitor.dsl

/**
 * Exception thrown when try to create node with invalid hostname.
 *
 * @param hostname Node hostname.
 */
class InvalidHostnameException(
    private val hostname: String
) : DslException() {
    override val message = "Hostname '$hostname' is invalid!"
}
