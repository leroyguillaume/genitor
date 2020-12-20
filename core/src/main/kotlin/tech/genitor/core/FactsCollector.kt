package tech.genitor.core

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
