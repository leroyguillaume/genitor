package tech.genitor.agent.facts

import tech.genitor.core.Facts

/**
 * Node facts.
 */
data class DefaultFacts(
    override val agentVersion: String
) : Facts
