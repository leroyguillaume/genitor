package tech.genitor.dsl

import tech.genitor.core.Node

/**
 * Group block.
 */
sealed class GroupBlock : InsurableBlock() {
    internal companion object {
        /**
         * Root group name.
         */
        private const val RootGroupName = "all"

        /**
         * Name pattern.
         */
        private val NamePattern = Regex("^([A-Za-z0-9_-]+)$")
    }

    /**
     * Root group block.
     */
    class Root internal constructor() : GroupBlock() {
        override val name = RootGroupName

        /**
         * Copy constructor.
         *
         * @param groupBlock Group block.
         */
        internal constructor(groupBlock: Root) : this() {
            copy(groupBlock, this)
        }
    }

    /**
     * Child group block.
     *
     * @param name Name.
     */
    class Child internal constructor(
        override val name: String
    ) : GroupBlock() {
        /**
         * Copy constructor.
         *
         * @param groupBlock Group block.
         */
        internal constructor(groupBlock: Child) : this(groupBlock.name) {
            copy(groupBlock, this)
        }
    }

    /**
     * Name.
     */
    internal abstract val name: String

    /**
     * Children by their name.
     */
    private val _children = mutableMapOf<String, Child>()

    /**
     * Children.
     */
    internal val children = _children.map { Child(it.value) }

    /**
     * Nodes.
     */
    private val _nodes = mutableSetOf<Node>()

    /**
     * Nodes.
     */
    internal val nodes: Set<Node> = _nodes

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
        _children[name] = Child(name).apply(block)
    }

    /**
     * Copy group block nodes and children in another one.
     *
     * This method modifies target group block. It is not thread safe. The only usage should be factorizing code between
     * sub-classes copy constructor. Please use it with caution.
     */
    protected fun copy(src: GroupBlock, target: GroupBlock) {
        target._nodes.clear()
        target._children.clear()
        target._nodes.addAll(src.nodes)
        target._children.putAll(src.children.map { it.name to it }.toMap())
    }
}
