package tech.genitor.dsl

import tech.genitor.core.Node

/**
 * Node block.
 *
 * @param hostname Hostname.
 */
class NodeBlock internal constructor(
    val hostname: String
) : InsurableBlock() {
    /**
     * Node.
     */
    val node = Node(hostname)

    /**
     * Copy constructor.
     *
     * @param nodeBlock Node block.
     */
    internal constructor(nodeBlock: NodeBlock) : this(nodeBlock.hostname) {
        val ensureBlock = nodeBlock.ensureBlock
        if (ensureBlock != null) {
            setEnsureBlock(ensureBlock)
        }
    }
}
