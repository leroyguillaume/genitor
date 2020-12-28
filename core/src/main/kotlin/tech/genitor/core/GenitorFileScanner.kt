package tech.genitor.core

import java.nio.file.Path

/**
 * Genitor project file scanner.
 */
interface GenitorFileScanner {
    /**
     * Scan directory and return project file.
     *
     * If no project file is found, default one is returned.
     *
     * @param dir Path to directory.
     * @return Project file.
     */
    fun scan(dir: Path): GenitorFile
}
