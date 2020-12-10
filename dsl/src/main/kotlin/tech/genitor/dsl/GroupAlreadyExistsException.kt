package tech.genitor.dsl

/**
 * Exception thrown when try to create a group with the same name from another one.
 *
 * @param name Group name.
 */
class GroupAlreadyExistsException(
    private val name: String
) : DslException() {
    override val message = "Group '$name' already exists!"
}
