package tech.genitor.core

import java.nio.file.Path

/**
 * Project.
 *
 * @param name Name.
 * @param namespace Namespace.
 * @param path Path to root directory.
 * @param entrypointScriptPath Path to entrypoint script.
 */
data class Project(
    val name: String,
    val namespace: ProjectNamespace,
    val path: Path,
    val entrypointScriptPath: Path
) {
    /**
     * Name with namespace.
     */
    val completeName = if (namespace.isEmpty) {
        name
    } else {
        "$namespace/$name"
    }
}
