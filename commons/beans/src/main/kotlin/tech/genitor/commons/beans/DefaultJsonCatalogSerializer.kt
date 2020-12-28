package tech.genitor.commons.beans

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import tech.genitor.core.*
import tech.genitor.dto.*

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
        project = project.toDto(),
        node = node.toDto(),
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
     * Convert project to DTO.
     *
     * @return Project DTO.
     */
    private fun Project.toDto() = ProjectDto(
        name = metadata.name,
        namespace = metadata.namespace
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
        next = next.map { it.toDto() }
    )
}
