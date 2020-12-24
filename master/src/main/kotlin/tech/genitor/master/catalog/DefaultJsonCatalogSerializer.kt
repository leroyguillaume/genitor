package tech.genitor.master.catalog

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.stereotype.Component
import tech.genitor.core.Catalog
import tech.genitor.core.JsonCatalogSerializer
import tech.genitor.core.Resource
import tech.genitor.core.ResourceGraph
import tech.genitor.dto.CatalogDto
import tech.genitor.dto.ResourceDto
import tech.genitor.dto.ResourceGraphDto

/**
 * Default implementation of JSON catalog serializer.
 *
 * @param mapper Object mapper.
 */
@Component
class DefaultJsonCatalogSerializer(
    private val mapper: ObjectMapper
) : JsonCatalogSerializer {
    override fun serialize(catalog: Catalog): String {
        val dto = catalog.toDto()
        return mapper.writeValueAsString(dto)
    }

    /**
     * Convert catalog to DTO.
     */
    private fun Catalog.toDto() = CatalogDto(
        graphs = graphs.map { it.toDto() }
    )

    /**
     * Convert resource to DTO.
     */
    private fun Resource.toDto() = ResourceDto(
        type = javaClass,
        params = mapper.convertValue(params)
    )

    /**
     * Convert resource graph to DTO.
     */
    private fun ResourceGraph.toDto(): ResourceGraphDto = ResourceGraphDto(
        resource = resource.toDto(),
        next = next.map { it.toDto() }
    )
}
