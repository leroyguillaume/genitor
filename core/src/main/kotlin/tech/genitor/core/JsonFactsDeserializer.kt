package tech.genitor.core

/**
 * JSON Facts deserializer.
 */
interface JsonFactsDeserializer {
    /**
     * Deserialize facts from JSON.
     *
     * @param factsJson Facts in JSON format.
     * @return Facts.
     */
    fun deserialize(factsJson: String): Facts
}
