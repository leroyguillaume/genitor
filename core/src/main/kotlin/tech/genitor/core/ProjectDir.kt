package tech.genitor.core

import java.nio.file.Path

/**
 * Project directory.
 *
 * @param project Project.
 * @param path Path to directory.
 * @param entrypointScriptPath Path to entrypoint script.
 */
data class ProjectDir(
    val project: Project,
    val path: Path,
    val entrypointScriptPath: Path
)
