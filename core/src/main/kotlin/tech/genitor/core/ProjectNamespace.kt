package tech.genitor.core

/**
 * Project namespace.
 *
 * @param names List of names.
 */
data class ProjectNamespace(
    val names: List<String> = emptyList()
) {
    /**
     * True if this namespace is empty, false otherwise.
     */
    val isEmpty = names.isEmpty()

    /**
     * Add name to namespace.
     *
     * @param name Name.
     * @return Copy of this namespace with added name.
     */
    fun addName(name: String) = copy(names = names + name)

    override fun toString() = names.joinToString("/")
}
