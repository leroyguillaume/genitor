package tech.genitor.master.catalog

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import tech.genitor.core.JsonResourceParamsSerializer
import tech.genitor.core.ResourceParams

/**
 * Default implementation of JSON resource parameters serializer.
 *
 * @param mapper Object mapper.
 */
@Component
class DefaultJsonResourceParamsSerializer(
    private val mapper: ObjectMapper
) : JsonResourceParamsSerializer {
    override fun serialize(params: ResourceParams): String = mapper.writeValueAsString(params)
}
