package tech.genitor.master.catalog

import tech.genitor.core.Facts
import tech.genitor.core.Node
import tech.genitor.core.ResourceGraph
import tech.genitor.core.ResourceGraphsBuilder
import tech.genitor.dsl.EnsureBlock

/**
 * Resource graphs builder from ensure blocks.
 *
 * @param node Node.
 * @param ensureBlocks Ensure blocks.
 */
class DslResourceGraphsBuilder internal constructor(
    override val node: Node,
    private val ensureBlocks: List<EnsureBlock>
) : ResourceGraphsBuilder {
    override fun build(facts: Facts): List<ResourceGraph> {
        val resourceHolders = ensureBlocks.flatMap { it.build(facts).resourceHolders }
        return resourceHolders.map { ResourceGraph(it.resource) }
    }

    /**
     * Add ensure block.
     *
     * @param ensureBlock Ensure block.
     * @return A copy of this builder with added ensure block.
     */
    internal fun addEnsureBlock(ensureBlock: EnsureBlock) = DslResourceGraphsBuilder(node, ensureBlocks + ensureBlock)
}
