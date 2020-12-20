package tech.genitor.master.facts

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import tech.genitor.core.FactsConsumer
import tech.genitor.core.FactsRepository

/**
 * Kafka implementation of facts consumer.
 *
 * @param factsRepository Facts repository.
 */
@Component
class KafkaFactsConsumer(
    private val factsRepository: FactsRepository
) : FactsConsumer {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(KafkaFactsConsumer::class.java)
    }

    @KafkaListener(topics = ["\${genitor.kafka.facts-topic.name}"])
    override fun consume(factsJson: String, @Header("hostname") hostname: String) {
        Logger.debug("Facts of node '$hostname' received ('$factsJson')")
        factsRepository.save(hostname, factsJson)
    }
}
