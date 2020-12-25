package tech.genitor.agent

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import tech.genitor.commons.beans.DefaultJsonCatalogDeserializer
import tech.genitor.commons.beans.DefaultJsonFactsSerializer

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
     * Create JSON facts serializer.
     *
     * @param mapper Object mapper.
     * @return JSON facts serializer.
     */
    @Bean
    fun jsonFactsSerializer(mapper: ObjectMapper) = DefaultJsonFactsSerializer(mapper)
}

/**
 * Start agent.
 *
 * @param args Command line arguments.
 */
fun main(args: Array<String>) {
    runApplication<GenitorAgent>(*args)
}
