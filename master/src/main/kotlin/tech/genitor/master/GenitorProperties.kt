package tech.genitor.master

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.nio.file.Path

/**
 * Genitor properties.
 *
 * @param deployDir Path to deploy directory.
 * @param kafka Kafka properties.
 */
@ConfigurationProperties(prefix = "genitor")
@ConstructorBinding
data class GenitorProperties(
    val deployDir: Path,
    val kafka: Kafka
) {
    /**
     * Kafka properties.
     *
     * @param factsTopic Facts topic configuration.
     * @param ssl SSL configuration.
     */
    data class Kafka(
        val factsTopic: Topic,
        val ssl: Ssl
    ) {
        /**
         * Kafka SSL configuration.
         *
         * @param keystore Path to keystore.
         * @param keystorePassword Keystore password.
         * @param truststore Path to truststore.
         * @param truststorePassword Truststore password.
         */
        data class Ssl(
            val keystore: Path,
            val keystorePassword: String,
            val truststore: Path,
            val truststorePassword: String
        )

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
