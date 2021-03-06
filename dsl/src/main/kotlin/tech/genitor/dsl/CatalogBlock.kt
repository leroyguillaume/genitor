package tech.genitor.dsl

import tech.genitor.core.Facts
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
    private val _rootGroupBlock = GroupBlock.Root()

    /**
     * Root group block.
     */
    val rootGroupBlock get() = GroupBlock.Root(_rootGroupBlock)

    /**
     * Node blocks by node hostname.
     */
    private val _nodeBlocks = mutableMapOf<String, NodeBlock>()

    /**
     * Node blocks.
     */
    val nodeBlocks get() = _nodeBlocks.map { NodeBlock(it.value) }

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
        _rootGroupBlock.group(name, block)
    }

    /**
     * Create node.
     *
     * @param hostname Hostname.
     * @param block Function to build resource graph on node.
     * @return Created node.
     * @throws InvalidHostnameException If hostname is invalid.
     * @throws NodeAlreadyExistsException If node already exists.
     */
    @Throws(DslException::class)
    fun node(hostname: String, block: EnsureBlock.(Facts) -> ResourceGraphDsl): Node {
        if (!HostnamePattern.matches(hostname)) {
            throw InvalidHostnameException(hostname)
        }
        if (_nodeBlocks.containsKey(hostname)) {
            throw NodeAlreadyExistsException(hostname)
        }
        val nodeBlock = NodeBlock(hostname).apply { ensure(block) }
        _nodeBlocks[hostname] = nodeBlock
        return nodeBlock.node
    }
}

/**
 * Create catalog block.
 *
 * @param block Function to build catalog.
 * @return Catalog block.
 */
fun catalog(block: CatalogBlock.() -> Unit) = CatalogBlock().apply(block)
