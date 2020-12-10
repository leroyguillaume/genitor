package tech.genitor.dsl

import tech.genitor.core.Node

/**
 * Catalog block.
 */
class CatalogBlock internal constructor() : InsurableBlock() {
    private companion object {
        /**
         * Hostname pattern.
         */
        private val HostnamePattern = Regex("^([A-Za-z0-9.-]+)$")
    }

    /**
     * Root group block.
     */
    private val rootGroupBlock = GroupBlock(GroupBlock.RootGroupName)

    /**
     * Node blocks by node hostname.
     */
    private val _nodeBlocks = mutableMapOf<String, NodeBlock>()

    /**
     * Create group.
     *
     * @param name Name.
     * @param block Function to build group.
     * @throws InvalidGroupNameException If group name is invalid.
     * @throws GroupAlreadyExistsException If group already exists.
     */
    @Throws(DslException::class)
    fun group(name: String, block: GroupBlock.() -> Unit) {
        rootGroupBlock.group(name, block)
    }

    /**
     * Create node.
     *
     * @param hostname Hostname.
     * @param block Function to build node.
     * @return Created node.
     * @throws InvalidHostnameException If hostname is invalid.
     * @throws NodeAlreadyExistsException If node already exists.
     */
    @Throws(DslException::class)
    fun node(hostname: String, block: NodeBlock.() -> Unit = {}): Node {
        if (!HostnamePattern.matches(hostname)) {
            throw InvalidHostnameException(hostname)
        }
        if (_nodeBlocks.containsKey(hostname)) {
            throw NodeAlreadyExistsException(hostname)
        }
        val nodeBlock = NodeBlock(hostname).apply(block)
        _nodeBlocks[hostname] = nodeBlock
        return nodeBlock.node
    }
}
