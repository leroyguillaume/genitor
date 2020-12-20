package tech.genitor.agent.facts

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import tech.genitor.core.Facts
import tech.genitor.core.JsonFactsSerializer

/**
 * Default implementation of JSON facts serializer.
 *
 * @param mapper Object mapper.
 */
@Component
class DefaultJsonFactsSerializer(
    private val mapper: ObjectMapper
) : JsonFactsSerializer {
    override fun serialize(facts: Facts): String = mapper.writeValueAsString(facts)
}
