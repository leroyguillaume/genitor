package tech.genitor.core

/**
 * Resource graph.
 *
 * @param resource Resource.
 * @param next Sub-graphs.
 */
data class ResourceGraph(
    val resource: Resource,
    val next: List<ResourceGraph>
)
