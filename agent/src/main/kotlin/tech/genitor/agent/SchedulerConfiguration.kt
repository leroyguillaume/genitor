package tech.genitor.agent

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import tech.genitor.agent.facts.FactsCollector
import tech.genitor.agent.facts.FactsProducer

/**
 * Scheduler configuration.
 *
 * @param factsCollector Facts collector.
 * @param factsProducer Facts producer.
 */
@Configuration
class SchedulerConfiguration(
    private val factsCollector: FactsCollector,
    private val factsProducer: FactsProducer
) {
    private companion object {
        private val Logger = LoggerFactory.getLogger(SchedulerConfiguration::class.java)
    }

    /**
     * Collect facts and send them to broker.
     */
    @Scheduled(fixedDelayString = "#{\${genitor.collect-facts-every} * 1000 * 60}")
    fun collectFacts() {
        Logger.debug("Starting facts collection")
        val facts = factsCollector.collect()
        factsProducer.send(facts)
    }
}
