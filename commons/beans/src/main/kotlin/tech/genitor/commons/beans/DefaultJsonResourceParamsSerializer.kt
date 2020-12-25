package tech.genitor.commons.beans

import com.fasterxml.jackson.databind.ObjectMapper
import tech.genitor.core.JsonResourceParamsSerializer
import tech.genitor.core.ResourceParams

/**
 * Default implementation of JSON resource parameters serializer.
 *
 * @param mapper Object mapper.
 */
class DefaultJsonResourceParamsSerializer(
    private val mapper: ObjectMapper
) : JsonResourceParamsSerializer {
    override fun serialize(params: ResourceParams): String = mapper.writeValueAsString(params)
}
