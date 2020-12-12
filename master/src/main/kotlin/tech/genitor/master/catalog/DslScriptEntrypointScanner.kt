package tech.genitor.master.catalog

import java.nio.file.Path

/**
 * DSL entrypoint script scanner.
 */
interface DslScriptEntrypointScanner {
    /**
     * Scan deploy directory and list path to DSL entrypoint script.
     *
     * @return Paths to DSL entrypoint script.
     */
    fun scan(): List<Path>
}
