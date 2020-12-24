package tech.genitor.master.catalog

import tech.genitor.core.*
import tech.genitor.dsl.EnsureBlock

/**
 * Catalog builder from ensure blocks.
 *
 * @param node Node.
 * @param ensureBlocks Ensure blocks.
 */
class DslCatalogBuilder internal constructor(
    override val node: Node,
    private val ensureBlocks: List<EnsureBlock>
) : CatalogBuilder {
    override fun build(facts: Facts): Catalog {
        val resourceHolders = ensureBlocks.flatMap { it.build(facts).resourceHolders }
        val graphs = resourceHolders.map { ResourceGraph(it.resource) }
        return Catalog(
            node = node,
            graphs = graphs
        )
    }

    /**
     * Add ensure block.
     *
     * @param ensureBlock Ensure block.
     * @return A copy of this builder with added ensure block.
     */
    internal fun addEnsureBlock(ensureBlock: EnsureBlock) = DslCatalogBuilder(node, ensureBlocks + ensureBlock)
}
