package tech.genitor.core

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
