package tech.genitor.core

/**
 * Catalog cache.
 */
interface CatalogCache {
    /**
     * Get most recent catalog.
     *
     * If no catalog in cache, empty catalog is returned.
     *
     * @return Catalog.
     */
    fun get(): Catalog

    /**
     * Save catalog.
     *
     * @param catalog Catalog.
     */
    fun save(catalog: Catalog)
}
