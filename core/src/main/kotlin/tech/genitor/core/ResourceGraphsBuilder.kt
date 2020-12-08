package tech.genitor.core

/**
 * Resource graphs builder.
 */
interface ResourceGraphsBuilder {
    /**
     * Build resource graphs from facts.
     *
     * @param facts Facts.
     * @return Resource graphs.
     */
    fun build(facts: Facts): List<ResourceGraph>
}
