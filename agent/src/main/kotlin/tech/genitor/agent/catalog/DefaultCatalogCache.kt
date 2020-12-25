package tech.genitor.agent.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.agent.AgentProperties
import tech.genitor.core.Catalog
import tech.genitor.core.CatalogCache
import tech.genitor.core.JsonCatalogDeserializer
import tech.genitor.core.Project
import java.nio.file.Files

/**
 * Default implementation of catalog cache.
 *
 * @param catalogDeserializer Catalog deserializer.
 * @param props Properties.
 */
@Component
class DefaultCatalogCache(
    private val catalogDeserializer: JsonCatalogDeserializer,
    private val props: AgentProperties
) : CatalogCache {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultCatalogCache::class.java)
    }

    override fun get(project: Project): Catalog? {
        val projectName = project.completeName
        val dir = props.catalogCacheDir.resolve(projectName)
        Logger.debug("Finding catalog '$projectName' from ${dir.toAbsolutePath()} directory")
        return if (!Files.isDirectory(dir)) {
            Logger.debug("${dir.toAbsolutePath()} is not a directory, no catalog '$projectName' in cache")
            null
        } else {
            Files.list(dir)
                .filter { it.fileName.endsWith(".json") }
                .sorted { path1, path2 ->
                    -Files.getLastModifiedTime(path1).compareTo(Files.getLastModifiedTime(path2))
                }
                .findFirst()
                .map { path ->
                    Logger.debug("${path.toAbsolutePath()} found as cache of catalog '$projectName'")
                    val catalogJson = Files.newInputStream(path).use { String(it.readBytes()) }
                    catalogDeserializer.deserialize(catalogJson)
                }
                .orElseGet {
                    Logger.debug("No cache found for catalog '$projectName'")
                    null
                }
        }
    }

    override fun save(catalog: Catalog) {

    }
}
