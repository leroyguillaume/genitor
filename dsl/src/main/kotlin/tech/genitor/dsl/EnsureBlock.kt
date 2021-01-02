package tech.genitor.dsl

import tech.genitor.core.Facts

/**
 * Ensure block.
 *
 * @param fn Function to build resource graph.
 */
class EnsureBlock internal constructor(
    private val fn: EnsureBlock.(Facts) -> ResourceGraphDsl
) {
    /**
     * Build resource graph.
     *
     * @param facts Node facts.
     * @return Resource graph.
     */
    fun build(facts: Facts) = fn(facts).build()
}
