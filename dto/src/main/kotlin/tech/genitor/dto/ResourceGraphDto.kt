package tech.genitor.dto

/**
 * Resource graph DTO.
 *
 * @param resource Resource.
 * @param whenSuccess Graph node resource to ensure if report status of the resource of this node is success.
 * @param whenChanged Graph node resource to ensure if report status of the resource of this node is changed.
 * @param whenUnchanged Graph node resource to ensure if report status of the resource of this node is unchanged.
 * @param whenFailure Graph node resource to ensure if report status of the resource of this node is failure.
 */
data class ResourceGraphDto(
    val resource: ResourceDto,
    val whenSuccess: ResourceGraphDto? = null,
    val whenChanged: ResourceGraphDto? = null,
    val whenUnchanged: ResourceGraphDto? = null,
    val whenFailure: ResourceGraphDto? = null
)
