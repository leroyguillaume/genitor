package tech.genitor.dto

/**
 * Catalog DTO.
 *
 * @param project Source project.
 * @param node Node targeted by the catalog.
 * @param graphs List of resource graphs.
 */
data class CatalogDto(
    val project: ProjectDto,
    val node: NodeDto,
    val graphs: List<ResourceGraphDto>
)
