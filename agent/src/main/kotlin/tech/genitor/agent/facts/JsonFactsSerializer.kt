package tech.genitor.agent.facts

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import tech.genitor.core.Facts
import tech.genitor.core.FactsSerializer

/**
 * JSON facts serializer.
 *
 * @param mapper Object mapper.
 */
@Component
class JsonFactsSerializer(
    private val mapper: ObjectMapper
) : FactsSerializer {
    override fun serialize(facts: Facts): String = mapper.writeValueAsString(facts)
}
