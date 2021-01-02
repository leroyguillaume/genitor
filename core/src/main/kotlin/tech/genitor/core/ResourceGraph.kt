package tech.genitor.core

/**
 * Resource graph.
 *
 * @param resource Resource.
 * @param whenSuccess Graph node resource to ensure if report status of the resource of this node is success.
 * @param whenChanged Graph node resource to ensure if report status of the resource of this node is changed.
 * @param whenUnchanged Graph node resource to ensure if report status of the resource of this node is unchanged.
 * @param whenFailure Graph node resource to ensure if report status of the resource of this node is failure.
 */
data class ResourceGraph(
    val resource: Resource,
    val whenSuccess: ResourceGraph? = null,
    val whenChanged: ResourceGraph? = null,
    val whenUnchanged: ResourceGraph? = null,
    val whenFailure: ResourceGraph? = null
)
