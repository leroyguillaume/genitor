package tech.genitor.agent.catalog

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component
import tech.genitor.agent.AgentProperties
import tech.genitor.commons.beans.NodeHeaderName
import tech.genitor.core.*

/**
 * Kafka implementation of catalog consumer.
 *
 * @param catalogDeserializer Catalog deserializer.
 * @param catalogExecutor Catalog executor.
 * @param props Properties.
 */
@Component
class KafkaCatalogConsumer(
    private val catalogDeserializer: JsonCatalogDeserializer,
    private val catalogCache: CatalogCache,
    private val catalogExecutor: CatalogExecutor,
    private val props: AgentProperties
) : CatalogConsumer {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(KafkaCatalogConsumer::class.java)
    }

    @KafkaListener(topics = ["\${genitor.kafka.catalog-topic}"])
    override fun consume(catalogJson: String, @Header(NodeHeaderName) hostname: String) {
        Logger.debug("Catalog received for node '$hostname' ('$catalogJson')")
        if (hostname == props.hostname) {
            try {
                val catalog = catalogDeserializer.deserialize(catalogJson)
                catalogCache.save(catalog)
                val report = catalogExecutor.execute(catalog)
            } catch (exception: ResourceInstantiationException) {
                Logger.error(exception.message, exception)
            }
        }
    }
}
