package tech.genitor.dto

/**
 * Catalog DTO.
 *
 * @param node Node targeted by the catalog.
 * @param graphs List of resource graphs.
 */
data class CatalogDto(
    val node: NodeDto,
    val graphs: List<ResourceGraphDto>
)
