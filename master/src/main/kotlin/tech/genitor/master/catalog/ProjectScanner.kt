package tech.genitor.master.catalog

import tech.genitor.core.Project

/**
 * Genitor project scanner.
 */
interface ProjectScanner {
    /**
     * Scan deploy directory and list projects.
     *
     * @return Projects.
     */
    fun scan(): List<Project>
}
