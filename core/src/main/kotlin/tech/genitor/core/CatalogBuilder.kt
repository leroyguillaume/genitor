package tech.genitor.core

/**
 * Catalog builder.
 */
interface CatalogBuilder {
    /**
     * Project.
     */
    val project: Project

    /**
     * Node.
     */
    val node: Node

    /**
     * Build resource graphs from facts.
     *
     * @param facts Facts.
     * @return Resource graphs.
     */
    fun build(facts: Facts): Catalog
}
