package tech.genitor.agent.facts

import tech.genitor.core.Facts

/**
 * Facts collector.
 */
interface FactsCollector {
    /**
     * Collect facts.
     *
     * @return Node facts.
     */
    fun collect(): Facts
}
