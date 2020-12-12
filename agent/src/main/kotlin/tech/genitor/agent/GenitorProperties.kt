package tech.genitor.agent

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.net.InetAddress
import java.nio.file.Path

/**
 * Genitor properties.
 *
 * @param hostname Hostname of machine.
 * @param collectFactsEvery Number of minutes between two facts collection.
 * @param kafka Kafka properties.
 */
@ConfigurationProperties(prefix = "genitor")
@ConstructorBinding
data class GenitorProperties(
    val hostname: String,
    val collectFactsEvery: Int,
    val kafka: Kafka
) {
    /**
     * Kafka properties.
     *
     * @param factsTopic Name of facts topic.
     * @param ssl SSL configuration.
     */
    data class Kafka(
        val factsTopic: String,
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
    }

    /**
     * Server properties.
     *
     * @param bindAddress Bind address.
     * @param bindPort Bind port.
     */
    data class Server(
        val bindAddress: InetAddress,
        val bindPort: Int
    )
}
