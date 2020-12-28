package tech.genitor.core

/**
 * Catalog cache.
 */
interface CatalogCache {
    /**
     * Get most recent catalog.
     *
     * @param projectMetadata Project metadata.
     * @return Catalog or null if it does not exist.
     */
    fun get(projectMetadata: ProjectMetadata): Catalog?

    /**
     * Save catalog.
     *
     * @param catalog Catalog.
     */
    fun save(catalog: Catalog)
}
