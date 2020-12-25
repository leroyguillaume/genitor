package tech.genitor.commons.beans

import com.fasterxml.jackson.databind.ObjectMapper
import tech.genitor.core.Facts
import tech.genitor.core.JsonFactsSerializer

/**
 * Default implementation of JSON facts serializer.
 *
 * @param mapper Object mapper.
 */
class DefaultJsonFactsSerializer(
    private val mapper: ObjectMapper
) : JsonFactsSerializer {
    override fun serialize(facts: Facts): String = mapper.writeValueAsString(facts)
}
