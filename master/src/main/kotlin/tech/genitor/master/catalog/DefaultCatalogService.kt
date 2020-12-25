package tech.genitor.master.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import tech.genitor.core.CatalogProducer
import tech.genitor.core.CatalogService
import tech.genitor.core.FactsRepository
import tech.genitor.core.ProjectDirScanner
import tech.genitor.dsl.DslCompiler
import tech.genitor.master.MasterProperties

/**
 * Default implementation of catalog service.
 *
 * @param projectDirScanner Project directory scanner.
 * @param dslCompiler DSL compiler.
 * @param factsRepository Facts repository.
 * @param catalogProducer Catalog producer.
 * @param props Properties.
 */
@Service
class DefaultCatalogService(
    private val projectDirScanner: ProjectDirScanner,
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
        val projectDirs = projectDirScanner.scan(props.deployDir)
        projectDirs.forEach { projectDir ->
            val projectName = projectDir.project.completeName
            Logger.debug("Compiling '$projectName'")
            val catalogBuilders = dslCompiler.compile(projectDir)
            if (catalogBuilders.isEmpty()) {
                Logger.warn("Project '$projectName' is empty")
            }
            catalogBuilders.forEach { builder ->
                Logger.debug("Fetching facts of node '${builder.node.hostname}'")
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
