package tech.genitor.core

/**
 * Catalog.
 *
 * @param project Source project.
 * @param node Node targeted by the catalog.
 * @param graphs Resource graphs.
 */
data class Catalog(
    val project: Project,
    val node: Node,
    val graphs: List<ResourceGraph>
)
