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
     * Add name to namespace.
     *
     * @param name Name.
     * @return Copy of this namespace with added name.
     */
    fun addName(name: String) = copy(names = names + name)

    override fun toString() = names.joinToString("/")
}
