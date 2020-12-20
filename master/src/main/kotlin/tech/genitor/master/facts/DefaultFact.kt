package tech.genitor.master.facts

import tech.genitor.core.Facts

/**
 * Default implementation of facts.
 *
 * @param agentVersion Version of agent installed on node.
 */
data class DefaultFact(
    override val agentVersion: String
) : Facts
