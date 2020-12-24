package tech.genitor.agent.facts

import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import tech.genitor.agent.AgentProperties
import tech.genitor.commons.config.NodeHeaderName
import tech.genitor.core.Facts
import tech.genitor.core.FactsProducer
import tech.genitor.core.JsonFactsSerializer

/**
 * Kafka implementation of facts producer.
 *
 * @param factsSerializer Facts serializer.
 * @param kafkaTemplate Kafka template.
 * @param props Properties.
 */
@Component
class KafkaFactsProducer(
    private val factsSerializer: JsonFactsSerializer,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val props: AgentProperties
) : FactsProducer {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(KafkaFactsProducer::class.java)
    }

    override fun send(facts: Facts) {
        val factsJson = factsSerializer.serialize(facts)
        Logger.debug("Facts serialized as JSON ('$factsJson')")
        val record = ProducerRecord<String, String>(props.kafka.factsTopic, factsJson).apply {
            headers().add(NodeHeaderName, props.hostname.toByteArray())
        }
        kafkaTemplate.send(record)
        Logger.info("Facts sent on topic '${props.kafka.factsTopic}'")
    }
}
