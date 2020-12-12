package tech.genitor.master.facts

/**
 * Facts repository.
 */
interface FactsRepository {
    /**
     * Save facts.
     *
     * @param hostname Node hostname.
     * @param factsJson Facts in JSON format.
     */
    fun save(hostname: String, factsJson: String)
}
