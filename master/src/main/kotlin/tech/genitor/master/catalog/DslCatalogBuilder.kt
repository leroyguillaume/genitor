package tech.genitor.master.catalog

import tech.genitor.core.Catalog
import tech.genitor.core.CatalogBuilder
import tech.genitor.core.Facts
import tech.genitor.core.Node
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
        val graphs = ensureBlocks.map { it.build(facts) }
        return Catalog(
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
