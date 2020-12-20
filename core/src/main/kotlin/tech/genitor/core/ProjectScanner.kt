package tech.genitor.core

import java.nio.file.Path

/**
 * Genitor project scanner.
 */
interface ProjectScanner {
    /**
     * Scan directory and list projects.
     *
     * @param dir Path to directory.
     * @return Projects.
     */
    fun scan(dir: Path): List<Project>
}
