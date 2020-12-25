package tech.genitor.core

import java.nio.file.Path

/**
 * Genitor project directory scanner.
 */
interface ProjectDirScanner {
    /**
     * Scan directory and list project directories.
     *
     * @param dir Path to directory.
     * @return Project directories.
     */
    fun scan(dir: Path): List<ProjectDir>
}
