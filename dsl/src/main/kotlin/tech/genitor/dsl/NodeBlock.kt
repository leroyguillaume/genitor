package tech.genitor.dsl

import tech.genitor.core.Node

/**
 * Node block.
 *
 * @param hostname Hostname.
 */
class NodeBlock internal constructor(
    internal val hostname: String
) : InsurableBlock() {
    /**
     * Node.
     */
    internal val node = Node(hostname)

    /**
     * Copy constructor.
     *
     * @param nodeBlock Node block.
     */
    internal constructor(nodeBlock: NodeBlock) : this(nodeBlock.hostname)
}
