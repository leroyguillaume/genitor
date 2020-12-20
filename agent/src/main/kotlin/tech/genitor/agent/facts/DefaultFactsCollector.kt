package tech.genitor.agent.facts

import org.springframework.stereotype.Component
import tech.genitor.core.FactsCollector

/**
 * Default implementation of facts collector.
 */
@Component
class DefaultFactsCollector : FactsCollector {
    override fun collect() = DefaultFacts(
        agentVersion = javaClass.`package`.implementationVersion ?: "dev"
    )
}
