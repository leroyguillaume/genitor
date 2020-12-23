package tech.genitor.commons.config

import java.net.InetAddress
import java.nio.file.Path

/**
 * Genitor properties.
 */
interface GenitorProperties {
    /**
     * Kafka properties.
     */
    interface Kafka {
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
         * Kafka servers comma-separated list.
         */
        val bootstrapServers: String

        /**
         * SSL configuration.
         */
        val ssl: Ssl
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

    /**
     * Server properties.
     */
    val server: Server

    /**
     * Kafka properties.
     */
    val kafka: Kafka
}
