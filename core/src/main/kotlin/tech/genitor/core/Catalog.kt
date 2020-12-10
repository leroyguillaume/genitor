package tech.genitor.core

/**
 * Catalog.
 *
 * @param rootGroup Root group of nodes.
 * @param resourceGraphsBuilder Resource graphs builder for each node.
 */
class Catalog(
    val rootGroup: Group,
    val resourceGraphsBuilder: List<ResourceGraphsBuilder>
)
