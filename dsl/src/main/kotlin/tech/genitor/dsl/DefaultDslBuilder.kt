package tech.genitor.dsl

import tech.genitor.core.Node
import tech.genitor.core.ResourceGraphsBuilder

/**
 * Default implementation of DSL builder.
 */
class DefaultDslBuilder : DslBuilder {
    override fun build(catalogBlock: CatalogBlock): List<ResourceGraphsBuilder> {
        val builderByHostname = builderByHostnameFromGroupBlock(
            groupBlock = catalogBlock.rootGroupBlock,
            builderByHostname = builderByHostnameFromNodeBlocks(catalogBlock.nodeBlocks)
        )
        return builderByHostname.values.toList()
    }

    /**
     * Get builder by node hostname from node blocks.
     *
     * @param nodeBlocks Node blocks.
     * @return Builder by node hostname.
     */
    private fun builderByHostnameFromNodeBlocks(nodeBlocks: List<NodeBlock>) = nodeBlocks
        .map { nodeBlock ->
            val ensureBlocks = nodeBlock.ensureBlock?.let { listOf(it) } ?: emptyList()
            nodeBlock.hostname to DslResourceGraphsBuilder(nodeBlock.node, ensureBlocks)
        }
        .toMap()

    /**
     * Get builder by node hostname recursively from group blocks.
     *
     * @param groupBlock Group block.
     * @param builderByHostname Builder by node hostname.
     * @return Builder by node hostname.
     * @throws UnknownNodeException If a node referenced in a group is not present in catalog.
     */
    @Throws(UnknownNodeException::class)
    private fun builderByHostnameFromGroupBlock(
        groupBlock: GroupBlock,
        builderByHostname: Map<String, DslResourceGraphsBuilder>
    ): Map<String, DslResourceGraphsBuilder> {
        val ensureBlock = groupBlock.ensureBlock
        val groupBuilderByHostname = if (ensureBlock == null) { // If ensure block is not defined, no change
            builderByHostname
        } else {                                                        // Else, update map to add it on each node
            val groupBuilderByHostname = groupBlock.allNodes()
                .map { node ->
                    val builder = builderByHostname[node.hostname] ?: throw UnknownNodeException(node.hostname)
                    node.hostname to builder.addEnsureBlock(ensureBlock)
                }
                .toMap()
            builderByHostname + groupBuilderByHostname
        }
        // Then, do it recursively on each child
        return groupBlock.children.fold(groupBuilderByHostname) { childrenBuilderByHostname, child ->
            childrenBuilderByHostname + builderByHostnameFromGroupBlock(child, childrenBuilderByHostname)
        }
    }

    /**
     * Get all nodes of a group recursively.
     *
     * @return All nodes.
     */
    private fun GroupBlock.allNodes(): Set<Node> = nodes + children.flatMap { it.allNodes() }
}
