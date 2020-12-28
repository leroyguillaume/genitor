package tech.genitor.core

/**
 * Catalog.
 *
 * @param graphs Resource graphs.
 */
data class Catalog(
    val graphs: List<ResourceGraph> = emptyList()
)
