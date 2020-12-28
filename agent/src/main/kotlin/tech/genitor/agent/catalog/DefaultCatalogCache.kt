package tech.genitor.agent.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.agent.AgentProperties
import tech.genitor.core.*
import java.nio.file.Files

/**
 * Default implementation of catalog cache.
 *
 * @param catalogSerializer Catalog serializer.
 * @param catalogDeserializer Catalog deserializer.
 * @param sha1Calculator SHA1 calculator.
 * @param props Properties.
 */
@Component
class DefaultCatalogCache(
    private val catalogSerializer: JsonCatalogSerializer,
    private val catalogDeserializer: JsonCatalogDeserializer,
    private val sha1Calculator: Sha1Calculator,
    private val props: AgentProperties
) : CatalogCache {
    private companion object {
        /**
         * JSON extension.
         */
        private const val JsonExtension = ".json"

        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultCatalogCache::class.java)
    }

    override fun get(projectMetadata: ProjectMetadata): Catalog? {
        val projectName = projectMetadata.completeName
        val subPath = projectName.replace(ProjectMetadata.NamespaceSeparator, "/")
        val dir = props.catalogsCacheDir.resolve(subPath).toAbsolutePath()
        Logger.debug("Finding catalog '$projectName' from $dir directory")
        return if (!Files.isDirectory(dir)) {
            Logger.debug("$dir is not a directory, no catalog '$projectName' in cache")
            null
        } else {
            Files.list(dir)
                .filter { it.fileName.endsWith(JsonExtension) }
                .sorted { path1, path2 ->
                    -Files.getLastModifiedTime(path1).compareTo(Files.getLastModifiedTime(path2))
                }
                .findFirst()
                .map { path ->
                    Logger.debug("${path.toAbsolutePath()} found as cache of catalog '$projectName'")
                    val catalogJson = Files.newBufferedReader(path).use { it.readText() }
                    catalogDeserializer.deserialize(catalogJson)
                }
                .orElseGet {
                    Logger.debug("No cache found for catalog '$projectName'")
                    null
                }
        }
    }

    override fun save(catalog: Catalog) {
        val projectName = catalog.project.metadata.completeName
        val subPath = projectName.replace(ProjectMetadata.NamespaceSeparator, "/")
        val dir = props.catalogsCacheDir.resolve(subPath).toAbsolutePath()
        if (!Files.isDirectory(dir)) {
            Logger.debug("Creating $dir directories")
            Files.createDirectories(dir)
            Logger.debug("Directories $dir created")
        }
        val catalogJson = catalogSerializer.serialize(catalog)
        Logger.debug("Catalog '$projectName' serialized as JSON ('$catalogJson')")
        val sha1 = sha1Calculator.compute(catalogJson.toByteArray())
        Logger.debug("SHA1 for catalog '$projectName' computed ('$sha1')")
        val path = dir.resolve("$sha1$JsonExtension")
        Logger.debug("Saving catalog '$projectName' to $path")
        Files.newBufferedWriter(path).use { it.write(catalogJson) }
        Logger.info("Catalog '$projectName' saved in cache ($path)")
    }
}
