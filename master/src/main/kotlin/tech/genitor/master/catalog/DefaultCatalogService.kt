package tech.genitor.master.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import tech.genitor.core.CatalogProducer
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
 * @param factsRepository Facts repository.
 * @param catalogProducer Catalog producer.
 * @param props Properties.
 */
@Service
class DefaultCatalogService(
    private val projectScanner: ProjectScanner,
    private val dslCompiler: DslCompiler,
    private val factsRepository: FactsRepository,
    private val catalogProducer: CatalogProducer,
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
            val catalogBuilders = dslCompiler.compile(project.entrypointScriptPath)
            if (catalogBuilders.isEmpty()) {
                Logger.warn("Catalog '${project.completeName}' is empty")
            }
            catalogBuilders.forEach { builder ->
                Logger.debug("Fetching facts for node '${builder.node.hostname}'")
                val facts = factsRepository.get(builder.node.hostname)
                if (facts == null) {
                    Logger.warn("No facts found for node '${builder.node.hostname}'")
                } else {
                    Logger.debug("Building catalog of node '${builder.node.hostname}'")
                    val catalog = builder.build(facts)
                    catalogProducer.send(catalog)
                }
            }
        }
    }
}
