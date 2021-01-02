package tech.genitor.commons.beans

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import tech.genitor.core.*
import tech.genitor.dto.CatalogDto
import tech.genitor.dto.NodeDto
import tech.genitor.dto.ResourceDto
import tech.genitor.dto.ResourceGraphDto

/**
 * Default implementation of JSON catalog serializer.
 *
 * @param mapper Object mapper.
 */
class DefaultJsonCatalogSerializer(
    private val mapper: ObjectMapper
) : JsonCatalogSerializer {
    override fun serialize(catalog: Catalog): String {
        val dto = catalog.toDto()
        return mapper.writeValueAsString(dto)
    }

    /**
     * Convert catalog to DTO.
     *
     * @return Catalog DTO.
     */
    private fun Catalog.toDto() = CatalogDto(
        graphs = graphs.map { it.toDto() }
    )

    /**
     * Convert node to DTO.
     *
     * @return Node DTO.
     */
    private fun Node.toDto() = NodeDto(
        hostname = hostname
    )

    /**
     * Convert resource to DTO.
     *
     * @return Resource DTO.
     */
    private fun Resource.toDto() = ResourceDto(
        type = javaClass,
        params = mapper.convertValue(params)
    )

    /**
     * Convert resource graph to DTO.
     *
     * @return Resource graph DTO.
     */
    private fun ResourceGraph.toDto(): ResourceGraphDto = ResourceGraphDto(
        resource = resource.toDto(),
        whenSuccess = whenSuccess?.toDto(),
        whenChanged = whenChanged?.toDto(),
        whenUnchanged = whenUnchanged?.toDto(),
        whenFailure = whenFailure?.toDto(),
    )
}
