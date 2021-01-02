package tech.genitor.commons.beans

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import tech.genitor.core.*
import tech.genitor.dto.CatalogDto
import tech.genitor.dto.NodeDto
import tech.genitor.dto.ResourceDto
import tech.genitor.dto.ResourceGraphDto

/**
 * Default implementation of JSON catalog deserializer.
 *
 * @param mapper Object mapper.
 */
class DefaultJsonCatalogDeserializer(
    private val mapper: ObjectMapper
) : JsonCatalogDeserializer {
    override fun deserialize(catalogJson: String): Catalog {
        val catalogDto = mapper.readValue<CatalogDto>(catalogJson)
        return catalogDto.toCatalog()
    }

    /**
     * Convert DTO to catalog.
     *
     * @return Catalog.
     * @throws ResourceInstantiationException If an error occurs during resource instantiation.
     */
    @Throws(ResourceInstantiationException::class)
    private fun CatalogDto.toCatalog() = Catalog(
        graphs = graphs.map { it.toResourceGraph() }
    )

    /**
     * Convert DTO to node.
     *
     * @return Node.
     * @throws ResourceInstantiationException If an error occurs during resource instantiation.
     */
    @Throws(ResourceInstantiationException::class)
    private fun NodeDto.toNode() = Node(
        hostname = hostname
    )

    /**
     * Convert DTO to resource.
     *
     * @return Resource.
     * @throws ResourceInstantiationException If an error occurs during resource instantiation.
     */
    @Throws(ResourceInstantiationException::class)
    private fun ResourceDto.toResource(): Resource {
        val paramsMemberName = Resource::params.name
        return try {
            val paramsType = type.declaredFields.firstOrNull { it.name == paramsMemberName }?.type
                ?: throw ResourceInstantiationException("$type has no member '$paramsMemberName'")
            val constructor = type.constructors.firstOrNull { constructor ->
                constructor.parameterCount == 1 && constructor.parameterTypes[0] == paramsType
            } ?: throw ResourceInstantiationException(
                message = "$type has no constructor with only one parameter of type $paramsType"
            )
            val params = try {
                mapper.convertValue(params, paramsType)
            } catch (exception: IllegalArgumentException) {
                throw ResourceInstantiationException("Unable to convert parameters map to $paramsType", exception)
            }
            try {
                constructor.newInstance(params) as Resource
            } catch (exception: Exception) {
                throw ResourceInstantiationException("Unable to instantiate $type", exception)
            }
        } catch (exception: SecurityException) {
            throw ResourceInstantiationException("Unable to get information of type $type", exception)
        }
    }

    /**
     * Convert DTO to resource graph.
     *
     * @return Resource graph.
     * @throws ResourceInstantiationException If an error occurs during resource instantiation.
     */
    @Throws(ResourceInstantiationException::class)
    private fun ResourceGraphDto.toResourceGraph(): ResourceGraph = ResourceGraph(
        resource = resource.toResource(),
        whenSuccess = whenSuccess?.toResourceGraph(),
        whenChanged = whenChanged?.toResourceGraph(),
        whenUnchanged = whenUnchanged?.toResourceGraph(),
        whenFailure = whenFailure?.toResourceGraph(),
    )
}
