package tech.genitor.agent.facts

import tech.genitor.core.Facts

/**
 * Facts serializer.
 */
interface FactsSerializer {
    /**
     * Serialize facts.
     *
     * @return Serialized facts.
     */
    fun serialize(facts: Facts): String
}
