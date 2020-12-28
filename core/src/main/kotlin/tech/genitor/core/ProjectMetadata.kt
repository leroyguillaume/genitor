package tech.genitor.core

/**
 * Project metadata.
 *
 * @param name Name.
 * @param namespace Namespace.
 */
data class ProjectMetadata(
    val name: String,
    val namespace: String?,
) {
    companion object {
        /**
         * Namespace separator.
         */
        const val NamespaceSeparator = "."

        /**
         * Namespace regex.
         */
        val NamespaceRegex = Regex("^([A-Za-z0-9]+\\$NamespaceSeparator)*([A-Za-z0-9]+)$")
    }

    /**
     * Name with namespace.
     */
    val completeName = if (namespace == null) {
        name
    } else {
        "$namespace$NamespaceSeparator$name"
    }
}
