package tech.genitor.core

/**
 * Catalog cache.
 */
interface CatalogCache {
    /**
     * Get most recent catalog.
     *
     * @param project Project.
     * @return Catalog or null if it does not exist.
     */
    fun get(project: Project): Catalog?

    /**
     * Save catalog.
     *
     * @param catalog Catalog.
     */
    fun save(catalog: Catalog)
}
