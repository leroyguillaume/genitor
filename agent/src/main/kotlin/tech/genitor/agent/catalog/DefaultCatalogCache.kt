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

    /**
     * Most recent catalog.
     */
    private lateinit var lastCatalog: Catalog

    override fun get(): Catalog = if (this::lastCatalog.isInitialized) {
        lastCatalog
    } else {
        val cacheDir = props.cacheDir.toAbsolutePath()
        Logger.debug("Finding catalog from cache directory ($cacheDir)")
        if (!Files.isDirectory(cacheDir)) {
            Logger.error("Unable to load catalog from cache: cache directory is not a directory ($cacheDir)")
            Catalog()
        } else {
            Files.list(cacheDir)
                .filter { it.fileName.toString().endsWith(JsonExtension) }
                .sorted { path1, path2 ->
                    -Files.getLastModifiedTime(path1).compareTo(Files.getLastModifiedTime(path2))
                }
                .findFirst()
                .map { path ->
                    Logger.debug("Loading catalog from cache ($path)")
                    val catalogJson = Files.newBufferedReader(path).use { it.readText() }
                    catalogDeserializer.deserialize(catalogJson).apply {
                        lastCatalog = this
                        Logger.debug("Catalog loaded from cache ($path)")
                    }
                }
                .orElseGet {
                    Logger.debug("No catalog in cache")
                    Catalog()
                }
        }
    }

    @Synchronized
    override fun save(catalog: Catalog) {
        if (this::lastCatalog.isInitialized && catalog == lastCatalog) {
            Logger.debug("Catalog is unchanged")
        } else {
            lastCatalog = catalog
            val cacheDir = props.cacheDir.toAbsolutePath()
            val cacheDirExists = Files.exists(cacheDir)
            if (cacheDirExists && !Files.isDirectory(cacheDir)) {
                Logger.error("Unable to save catalog in cache: cache directory is not a directory ($cacheDir)")
            } else {
                if (!cacheDirExists) {
                    Logger.debug("Creating cache directory recursively ($cacheDir)")
                    Files.createDirectories(cacheDir)
                    Logger.debug("Cache directory created ($cacheDir)")
                }
                val catalogJson = catalogSerializer.serialize(catalog)
                Logger.debug("Catalog serialized as JSON ('$catalogJson')")
                val sha1 = sha1Calculator.compute(catalogJson.toByteArray())
                Logger.debug("Catalog SHA1 computed ('$sha1')")
                val path = cacheDir.resolve("$sha1$JsonExtension")
                Logger.debug("Saving catalog in cache ($path)")
                Files.newBufferedWriter(path).use { it.write(catalogJson) }
                Logger.debug("Catalog saved in cache ($path)")
            }
        }
    }
}
