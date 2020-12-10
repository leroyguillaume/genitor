package tech.genitor.dsl

import tech.genitor.core.ResourceGraphsBuilder

/**
 * DSL builder.
 */
interface DslBuilder {
    /**
     * Build DSL.
     *
     * @param catalogBlock Catalog block.
     * @return Resource graphs builder for each node.
     * @throws UnknownNodeException If a group references a node which is not present in catalog.
     */
    @Throws(UnknownNodeException::class)
    fun build(catalogBlock: CatalogBlock): List<ResourceGraphsBuilder>
}
