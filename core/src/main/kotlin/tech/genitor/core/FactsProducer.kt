package tech.genitor.core

/**
 * Facts producer.
 */
interface FactsProducer {
    /**
     * Send facts to broker.
     *
     * @param facts Facts.
     */
    fun send(facts: Facts)
}
