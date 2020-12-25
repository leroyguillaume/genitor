package tech.genitor.commons.beans

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import tech.genitor.core.Facts
import tech.genitor.core.JsonFactsDeserializer
import tech.genitor.dto.FactsDto

/**
 * Default implementation of JSON facts deserializer.
 *
 * @param mapper Object mapper.
 */
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
    private fun FactsDto.toFacts() = Facts(
        agentVersion = agentVersion
    )
}
