package tech.genitor.core

/**
 * Group of nodes.
 *
 * @param name Name.
 * @param nodes Nodes.
 * @param children Children.
 */
data class Group(
    val name: String,
    val nodes: Set<Node>,
    val children: Set<Group>
)
