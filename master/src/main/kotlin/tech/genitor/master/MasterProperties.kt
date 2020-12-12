package tech.genitor.master

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import tech.genitor.commons.config.GenitorProperties
import java.nio.file.Path

/**
 * Master properties.
 *
 * @param deployDir Path to deploy directory.
 * @param server Server properties.
 * @param kafka Kafka properties.
 */
@ConfigurationProperties(prefix = "genitor")
@ConstructorBinding
data class MasterProperties(
    val deployDir: Path,
    override val server: GenitorProperties.Server,
    override val kafka: Kafka
) : GenitorProperties {
    /**
     * Kafka properties.
     *
     * @param factsTopic Facts topic configuration.
     * @param ssl SSL configuration.
     */
    data class Kafka(
        val factsTopic: Topic,
        override val ssl: GenitorProperties.Kafka.Ssl
    ) : GenitorProperties.Kafka {
        /**
         * Kafka topic configuration.
         *
         * @param name Name.
         * @param partitions Number of partitions.
         * @param replicas Number of replicas.
         */
        data class Topic(
            val name: String,
            val partitions: Int,
            val replicas: Int
        )
    }
}
