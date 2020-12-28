package tech.genitor.core

import java.nio.file.Path

/**
 * Genitor project file.
 *
 * @param projectMetadata Project metadata.
 * @param rootDir Path to project root directory.
 * @param catalogPath Path to catalog DSL script file.
 */
data class GenitorFile(
    val projectMetadata: ProjectMetadata,
    val rootDir: Path,
    val catalogPath: Path
)
