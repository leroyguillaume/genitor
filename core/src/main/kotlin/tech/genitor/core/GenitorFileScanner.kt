package tech.genitor.core

import java.nio.file.Path

/**
 * Genitor project file scanner.
 */
interface GenitorFileScanner {
    /**
     * Scan directory and list project files.
     *
     * @param dir Path to directory.
     * @return Project files.
     */
    fun scan(dir: Path): List<GenitorFile>
}
