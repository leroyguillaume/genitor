package tech.genitor.agent.facts

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import tech.genitor.agent.GenitorProperties
import tech.genitor.core.Facts

/**
 * Kafka implementation of facts producer.
 *
 * @param kafkaTemplate Kafka template.
 * @param props Properties.
 */
@Component
class KafkaFactsProducer(
    private val factsSerializer: FactsSerializer,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val props: GenitorProperties
) : FactsProducer {
    private companion object {
        private val Logger = LoggerFactory.getLogger(KafkaFactsProducer::class.java)
    }

    override fun send(facts: Facts) {
        val serializedFacts = factsSerializer.serialize(facts)
        Logger.debug("Facts serialized as '$serializedFacts'")
        kafkaTemplate.send(props.kafka.factsTopic, serializedFacts)
        Logger.info("Facts sent on topic '${props.kafka.factsTopic}'")
    }
}
