package tech.genitor.core

/**
 * JSON Facts serializer.
 */
interface JsonFactsSerializer {
    /**
     * Serialize facts to JSON format.
     *
     * @param facts Facts.
     * @return Serialized facts.
     */
    fun serialize(facts: Facts): String
}
