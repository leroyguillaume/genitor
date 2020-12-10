package tech.genitor.dsl

import tech.genitor.core.Node

/**
 * Group block.
 *
 * @param name Name.
 */
class GroupBlock internal constructor(
    internal val name: String
) : InsurableBlock() {
    internal companion object {
        /**
         * Name pattern.
         */
        private val NamePattern = Regex("^([A-Za-z0-9_-]+)$")

        /**
         * Root group name.
         */
        internal const val RootGroupName = "all"
    }

    /**
     * Children by their name.
     */
    private val _children = mutableMapOf<String, GroupBlock>()

    /**
     * Children.
     */
    private val children = _children.map { GroupBlock(it.value) }

    /**
     * Nodes.
     */
    private val _nodes = mutableSetOf<Node>()

    /**
     * Nodes.
     */
    internal val nodes: Set<Node> = _nodes

    /**
     * Copy constructor.
     *
     * @param groupBlock Group block.
     */
    internal constructor(groupBlock: GroupBlock) : this(groupBlock.name) {
        _nodes.addAll(groupBlock._nodes)
        _children.putAll(groupBlock._children)
    }

    /**
     * Add nodes.
     *
     * @param nodes Nodes.
     */
    fun add(vararg nodes: Node) {
        _nodes.addAll(nodes)
    }

    /**
     * Create child.
     *
     * @param name Name.
     * @param block Function to build child.
     * @throws InvalidGroupNameException If group name is invalid.
     * @throws GroupAlreadyExistsException If group already exists.
     */
    @Throws(DslException::class)
    fun group(name: String, block: GroupBlock.() -> Unit) {
        if (!NamePattern.matches(name) || name == RootGroupName) {
            throw InvalidGroupNameException(name)
        }
        if (_children.containsKey(name)) {
            throw GroupAlreadyExistsException(name)
        }
        _children[name] = GroupBlock(name).apply(block)
    }
}
