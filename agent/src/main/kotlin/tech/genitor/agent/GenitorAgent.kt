package tech.genitor.agent

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import tech.genitor.commons.beans.DefaultJsonCatalogDeserializer
import tech.genitor.commons.beans.DefaultJsonCatalogSerializer
import tech.genitor.commons.beans.DefaultJsonFactsSerializer
import tech.genitor.commons.beans.DefaultSha1Calculator

/**
 * Genitor Agent application.
 */
@ConfigurationPropertiesScan
@EnableScheduling
@SpringBootApplication
class GenitorAgent {
    /**
     * Create JSON catalog deserializer.
     *
     * @param mapper Object mapper.
     * @return JSON catalog deserializer.
     */
    @Bean
    fun jsonCatalogDeserializer(mapper: ObjectMapper) = DefaultJsonCatalogDeserializer(mapper)

    /**
     * Create JSON catalog serializer.
     *
     * @param mapper Object mapper.
     * @return JSON catalog serializer.
     */
    @Bean
    fun jsonCatalogSerializer(mapper: ObjectMapper) = DefaultJsonCatalogSerializer(mapper)

    /**
     * Create JSON facts serializer.
     *
     * @param mapper Object mapper.
     * @return JSON facts serializer.
     */
    @Bean
    fun jsonFactsSerializer(mapper: ObjectMapper) = DefaultJsonFactsSerializer(mapper)

    /**
     * Create SHA1 calculator.
     *
     * @return SHA1 calculator.
     */
    @Bean
    fun sha1Calculator() = DefaultSha1Calculator()
}

/**
 * Start agent.
 *
 * @param args Command line arguments.
 */
fun main(args: Array<String>) {
    runApplication<GenitorAgent>(*args)
}
