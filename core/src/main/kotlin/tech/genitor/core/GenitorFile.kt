package tech.genitor.core

import java.nio.file.Path

/**
 * Genitor project file.
 *
 * @param project Project.
 * @param rootDir Path to project root directory.
 * @param catalogPath Path to catalog DSL script file.
 */
data class GenitorFile(
    val project: Project,
    val rootDir: Path,
    val catalogPath: Path
)
