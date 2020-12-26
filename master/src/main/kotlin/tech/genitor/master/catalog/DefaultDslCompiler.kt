package tech.genitor.master.catalog

import de.swirtz.ktsrunner.objectloader.KtsObjectLoader
import org.springframework.stereotype.Component
import tech.genitor.core.CatalogBuilder
import tech.genitor.core.GenitorFile
import tech.genitor.core.Node
import tech.genitor.core.Project
import tech.genitor.dsl.*
import java.nio.file.Files

/**
 * Default implementation of DSL compiler.
 */
@Component
class DefaultDslCompiler : DslCompiler {
    override fun compile(genitorFile: GenitorFile): List<CatalogBuilder> {
        val objectLoader = KtsObjectLoader()
        val catalogBlock = Files.newBufferedReader(genitorFile.catalogPath).use {
            objectLoader.load<CatalogBlock>(it)
        }
        val builderByHostname = builderByHostnameFromGroupBlock(
            groupBlock = catalogBlock.rootGroupBlock,
            builderByHostname = builderByHostnameFromNodeBlocks(genitorFile.project, catalogBlock.nodeBlocks)
        )
        return builderByHostname.values.toList()
    }

    /**
     * Get builder by node hostname from node blocks.
     *
     * @param project Project.
     * @param nodeBlocks Node blocks.
     * @return Builder by node hostname.
     */
    private fun builderByHostnameFromNodeBlocks(project: Project, nodeBlocks: List<NodeBlock>) = nodeBlocks
        .map { nodeBlock ->
            val ensureBlocks = nodeBlock.ensureBlock?.let { listOf(it) } ?: emptyList()
            nodeBlock.hostname to DslCatalogBuilder(project, nodeBlock.node, ensureBlocks)
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
        builderByHostname: Map<String, DslCatalogBuilder>
    ): Map<String, DslCatalogBuilder> {
        val ensureBlock = groupBlock.ensureBlock
        val groupBuilderByHostname = if (ensureBlock == null) { // If ensure block is not defined, no change
            builderByHostname
        } else {                                                // Else, update map to add it on each node
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
