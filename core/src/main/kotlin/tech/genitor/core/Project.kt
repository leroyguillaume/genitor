package tech.genitor.core

import java.nio.file.Path

/**
 * Project.
 *
 * @param name Name.
 * @param namespace Namespace.
 * @param path Path to root directory.
 */
data class Project(
    val name: String,
    val namespace: ProjectNamespace,
    val path: Path
)
