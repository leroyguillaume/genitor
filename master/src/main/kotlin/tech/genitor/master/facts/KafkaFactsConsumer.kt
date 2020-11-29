package tech.genitor.master.facts

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

/**
 * Kafka implementation of facts consumer.
 */
@Component
class KafkaFactsConsumer : FactsConsumer {
    private companion object {
        private val Logger = LoggerFactory.getLogger(KafkaFactsConsumer::class.java)
    }

    @KafkaListener(topics = ["\${genitor.kafka.facts-topic}"])
    override fun consume(factsJson: String) {
        Logger.info(factsJson)
    }
}
