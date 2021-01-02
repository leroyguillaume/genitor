package tech.genitor.agent

import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import tech.genitor.core.CatalogCache
import tech.genitor.core.CatalogExecutor
import tech.genitor.core.FactsCollector
import tech.genitor.core.FactsProducer

/**
 * Scheduler configuration.
 *
 * @param factsCollector Facts collector.
 * @param factsProducer Facts producer.
 * @param catalogCache Catalog cache.
 * @param catalogExecutor Catalog executor.
 */
@Configuration
class SchedulerConfiguration(
    private val factsCollector: FactsCollector,
    private val factsProducer: FactsProducer,
    private val catalogCache: CatalogCache,
    private val catalogExecutor: CatalogExecutor
) {
    /**
     * Collect facts and send them to broker.
     */
    @Scheduled(fixedDelayString = "#{\${genitor.collect-facts-every} * 1000 * 60}")
    fun collectFacts() {
        val facts = factsCollector.collect()
        factsProducer.send(facts)
    }

    /**
     * Execute catalog.
     */
    @Scheduled(fixedDelayString = "#{\${genitor.execute-catalog-every} * 1000 * 60}")
    fun executeCatalog() {
        val catalog = catalogCache.get()
        catalogExecutor.execute(catalog)
    }
}
