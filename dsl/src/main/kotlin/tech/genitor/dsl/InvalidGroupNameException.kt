package tech.genitor.dsl

/**
 * Exception thrown when try to create group with invalid name.
 *
 * @param name Group name.
 */
class InvalidGroupNameException(
    private val name: String
) : DslException() {
    override val message = "Group name '$name' is invalid!"
}
