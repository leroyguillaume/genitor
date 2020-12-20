package tech.genitor.core

/**
 * Facts repository.
 */
interface FactsRepository {
    /**
     * Get facts by node hostname.
     *
     * @param hostname Node hostname.
     * @return Facts of node or null if node does not exist.
     */
    fun get(hostname: String): Facts?

    /**
     * Save facts.
     *
     * @param hostname Node hostname.
     * @param factsJson Facts in JSON format.
     */
    fun save(hostname: String, factsJson: String)
}
