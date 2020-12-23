package tech.genitor.agent

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import tech.genitor.commons.config.GenitorProperties

/**
 * Agent properties.
 *
 * @param hostname Hostname of machine.
 * @param collectFactsEvery Number of minutes between two facts collection.
 * @param server Server properties.
 * @param kafka Kafka properties.
 */
@ConfigurationProperties(prefix = "genitor")
@ConstructorBinding
data class AgentProperties(
    val hostname: String,
    val collectFactsEvery: Int,
    override val server: GenitorProperties.Server,
    override val kafka: Kafka
) : GenitorProperties {
    /**
     * Kafka properties.
     *
     * @param bootstrapServers Kafka servers comma-separated list.
     * @param factsTopic Name of facts topic.
     * @param ssl SSL configuration.
     */
    data class Kafka(
        override val bootstrapServers: String,
        val factsTopic: String,
        override val ssl: GenitorProperties.Kafka.Ssl
    ) : GenitorProperties.Kafka
}
