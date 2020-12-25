package tech.genitor.core

/**
 * Project.
 *
 * @param name Name.
 * @param namespace Namespace.
 */
data class Project(
    val name: String,
    val namespace: String,
) {
    companion object {
        /**
         * Root namespace.
         */
        const val RootNamespace = "/"

        /**
         * Namespace separator.
         */
        const val NamespaceSeparator = "/"
    }

    /**
     * Name with namespace.
     */
    val completeName = if (namespace == RootNamespace) {
        name
    } else {
        "$namespace/$name"
    }
}
