package tech.genitor.master.facts

/**
 * Facts consumer.
 */
interface FactsConsumer {
    /**
     * Consume facts in JSON format.
     *
     * @param factsJson Facts in JSON format.
     * @param hostname Node hostname.
     */
    fun consume(factsJson: String, hostname: String)
}
