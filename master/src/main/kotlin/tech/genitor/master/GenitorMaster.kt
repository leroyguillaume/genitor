package tech.genitor.master

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import tech.genitor.commons.beans.DefaultJsonCatalogSerializer
import tech.genitor.commons.beans.DefaultJsonFactsDeserializer
import tech.genitor.commons.beans.DefaultJsonResourceParamsSerializer

/**
 * Genitor master application.
 */
@ConfigurationPropertiesScan
@SpringBootApplication
class GenitorMaster {
    /**
     * Create JSON catalog serializer.
     *
     * @param mapper Object mapper.
     * @return JSON catalog serializer.
     */
    @Bean
    fun jsonCatalogSerializer(mapper: ObjectMapper) = DefaultJsonCatalogSerializer(mapper)

    /**
     * Create JSON facts deserializer.
     *
     * @param mapper Object mapper.
     * @return JSON facts deserializer.
     */
    @Bean
    fun jsonFactsDeserializer(mapper: ObjectMapper) = DefaultJsonFactsDeserializer(mapper)

    /**
     * Create JSON resource parameters serializer.
     *
     * @param mapper Object mapper.
     * @return JSON resource parameters serializer.
     */
    @Bean
    fun jsonResourceParamsSerializer(mapper: ObjectMapper) = DefaultJsonResourceParamsSerializer(mapper)
}

/**
 * Start master.
 *
 * @param args Command line arguments.
 */
fun main(args: Array<String>) {
    runApplication<GenitorMaster>(*args)
}
