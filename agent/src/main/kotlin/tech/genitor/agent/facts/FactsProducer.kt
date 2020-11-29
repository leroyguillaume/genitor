package tech.genitor.agent.facts

import tech.genitor.core.Facts

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
