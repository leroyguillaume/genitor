package tech.genitor.master.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import tech.genitor.dsl.DslCompiler

/**
 * Default implementation of catalog service.
 *
 * @param projectScanner Project scanner.
 * @param dslCompiler DSL compiler.
 */
@Service
class DefaultCatalogService(
    private val projectScanner: ProjectScanner,
    private val dslCompiler: DslCompiler
) : CatalogService {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultCatalogService::class.java)
    }

    override fun deploy() {
        Logger.debug("Starting deployment")
        val projects = projectScanner.scan()
        projects.forEach { project ->
            Logger.debug("Compiling ${project.completeName}")
            val resourceGraphsBuilder = dslCompiler.compile(project.entrypointScriptPath)
        }
    }
}
