package tech.genitor.master.catalog

import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import tech.genitor.commons.config.NodeHeaderName
import tech.genitor.core.Catalog
import tech.genitor.core.CatalogProducer
import tech.genitor.core.JsonCatalogSerializer
import tech.genitor.master.MasterProperties

/**
 * Kafka implementation of catalog producer.
 *
 * @param catalogSerializer Catalog serializer.
 * @param kafkaTemplate Kafka template.
 * @param props Properties.
 */
@Component
class KafkaCatalogProducer(
    private val catalogSerializer: JsonCatalogSerializer,
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val props: MasterProperties
) : CatalogProducer {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(KafkaCatalogProducer::class.java)
    }

    override fun send(catalog: Catalog) {
        val catalogJson = catalogSerializer.serialize(catalog)
        Logger.debug("Catalog serialized as JSON ('$catalogJson')")
        val record = ProducerRecord<String, String>(props.kafka.catalogTopic.name, catalogJson).apply {
            headers().add(NodeHeaderName, catalog.node.hostname.toByteArray())
        }
        kafkaTemplate.send(record)
        Logger.info("Catalog sent on topic '${props.kafka.catalogTopic.name}'")
    }
}
