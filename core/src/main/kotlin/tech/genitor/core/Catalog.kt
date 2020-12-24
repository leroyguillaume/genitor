package tech.genitor.core

/**
 * Catalog.
 *
 * @param node Node.
 * @param graphs Resource graphs.
 */
data class Catalog(
    val node: Node,
    val graphs: List<ResourceGraph>
)
