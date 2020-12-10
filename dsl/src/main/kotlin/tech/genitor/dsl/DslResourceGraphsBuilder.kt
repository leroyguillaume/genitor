package tech.genitor.dsl

import tech.genitor.core.Facts
import tech.genitor.core.Node
import tech.genitor.core.ResourceGraph
import tech.genitor.core.ResourceGraphsBuilder

/**
 * Resource graphs builder from ensure block.
 *
 * @param node Node.
 * @param ensureBlock Ensure block.
 */
class DslResourceGraphsBuilder(
    override val node: Node,
    private val ensureBlock: EnsureBlock
) : ResourceGraphsBuilder {
    override fun build(facts: Facts): List<ResourceGraph> {
        val resourceHolders = ensureBlock.build(facts).resourceHolders
        return resourceHolders.map { ResourceGraph(it.resource) }
    }
}
