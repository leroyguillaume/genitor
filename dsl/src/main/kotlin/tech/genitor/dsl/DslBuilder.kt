package tech.genitor.dsl

import tech.genitor.core.Catalog

/**
 * DSL builder.
 */
interface DslBuilder {
    /**
     * Build DSL.
     *
     * @param catalogBlock Catalog block.
     * @return Built catalog.
     */
    fun build(catalogBlock: CatalogBlock): Catalog
}
