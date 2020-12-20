package tech.genitor.master.facts

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import tech.genitor.core.Facts
import tech.genitor.core.JsonFactsDeserializer
import tech.genitor.dto.FactsDto

/**
 * Default implementation of JSON facts deserializer.
 *
 * @param mapper Object mapper.
 */
@Component
class DefaultJsonFactsDeserializer(
    private val mapper: ObjectMapper
) : JsonFactsDeserializer {
    override fun deserialize(factsJson: String): Facts {
        val factsDto = mapper.readValue<FactsDto>(factsJson)
        return factsDto.toFacts()
    }

    /**
     * Convert facts DTO to facts.
     */
    private fun FactsDto.toFacts() = DefaultFact(
        agentVersion = agentVersion
    )
}
