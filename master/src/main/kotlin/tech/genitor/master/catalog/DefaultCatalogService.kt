package tech.genitor.master.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import tech.genitor.core.CatalogService
import tech.genitor.core.FactsRepository
import tech.genitor.core.ProjectScanner
import tech.genitor.dsl.DslCompiler
import tech.genitor.master.MasterProperties

/**
 * Default implementation of catalog service.
 *
 * @param projectScanner Project scanner.
 * @param dslCompiler DSL compiler.
 * @param props Properties.
 */
@Service
class DefaultCatalogService(
    private val projectScanner: ProjectScanner,
    private val dslCompiler: DslCompiler,
    private val factsRepository: FactsRepository,
    private val props: MasterProperties
) : CatalogService {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultCatalogService::class.java)
    }

    override fun deploy() {
        Logger.debug("Starting deployment")
        val projects = projectScanner.scan(props.deployDir)
        projects.forEach { project ->
            Logger.debug("Compiling ${project.completeName}")
            val resourceGraphsBuilder = dslCompiler.compile(project.entrypointScriptPath)
            resourceGraphsBuilder.forEach { builder ->
                val facts = factsRepository.get(builder.node.hostname)
                if (facts == null) {
                    Logger.warn("Node '${builder.node.hostname}' does not exist")
                } else {
                    Logger.debug("Building resource graphs of node '${builder.node.hostname}'")
                    val graphs = builder.build(facts)
                }
            }
        }
    }
}
