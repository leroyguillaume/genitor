package tech.genitor.core

/**
 * Catalog executor.
 */
interface CatalogExecutor {
    /**
     * Execute catalog.
     *
     * @param catalog Catalog.
     * @return Catalog report.
     */
    fun execute(catalog: Catalog): CatalogReport
}
