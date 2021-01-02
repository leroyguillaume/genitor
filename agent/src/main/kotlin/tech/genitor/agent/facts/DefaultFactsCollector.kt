package tech.genitor.agent.facts

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.core.Facts
import tech.genitor.core.FactsCollector

/**
 * Default implementation of facts collector.
 */
@Component
class DefaultFactsCollector : FactsCollector {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultFactsCollector::class.java)
    }

    override fun collect(): Facts {
        Logger.debug("Starting facts collection")
        return Facts(
            agentVersion = javaClass.`package`.implementationVersion ?: "dev"
        )
    }
}
