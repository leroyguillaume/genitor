package tech.genitor.dto

/**
 * Resource graph DTO.
 *
 * @param resource Resource.
 * @param next Sub-graphs.
 */
data class ResourceGraphDto(
    val resource: ResourceDto,
    val next: List<ResourceGraphDto>
)
