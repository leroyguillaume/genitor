package tech.genitor.master.facts

/**
 * Facts consumer.
 */
interface FactsConsumer {
    /**
     * Consume facts in JSON format.
     *
     * @param factsJson Facts in JSON format.
     */
    fun consume(factsJson: String)
}
