package tech.genitor.dsl

import tech.genitor.core.Resource
import tech.genitor.core.ResourceGraph

/**
 * Resource graph DSL.
 *
 * @param resource Resource.
 * @param whenSuccess Graph node resource to ensure if report status of the resource of this node is success.
 * @param whenChanged Graph node resource to ensure if report status of the resource of this node is changed.
 * @param whenUnchanged Graph node resource to ensure if report status of the resource of this node is unchanged.
 * @param whenFailure Graph node resource to ensure if report status of the resource of this node is failure.
 */
class ResourceGraphDsl(
    val resource: Resource,
    val whenSuccess: ResourceGraphDsl? = null,
    val whenChanged: ResourceGraphDsl? = null,
    val whenUnchanged: ResourceGraphDsl? = null,
    val whenFailure: ResourceGraphDsl? = null
) {
    /**
     * Define graph node resource to ensure if report status of the resource of this node is failure.
     *
     * @param graph Resource graph.
     * @return Copy of this graph.
     */
    fun whenFailure(graph: ResourceGraphDsl) = ResourceGraphDsl(
        resource = resource,
        whenSuccess = whenSuccess,
        whenChanged = whenChanged,
        whenUnchanged = whenUnchanged,
        whenFailure = graph
    )

    /**
     * Define graph node resource to ensure if report status the resource of this node is changed.
     *
     * @param graph Resource graph.
     * @return Copy of this graph.
     */
    fun whenChanged(graph: ResourceGraphDsl) = ResourceGraphDsl(
        resource = resource,
        whenSuccess = whenSuccess,
        whenChanged = graph,
        whenUnchanged = whenUnchanged,
        whenFailure = whenFailure
    )

    /**
     * Define graph node resource to ensure if report status of the resource of this node is success.
     *
     * @param graph Resource graph.
     * @return Copy of this graph.
     */
    fun whenSuccess(graph: ResourceGraphDsl) = ResourceGraphDsl(
        resource = resource,
        whenSuccess = graph,
        whenChanged = whenChanged,
        whenUnchanged = whenUnchanged,
        whenFailure = whenFailure
    )

    /**
     * Define graph node resource to ensure if report status of the resource of this node is unchanged.
     *
     * @param graph Resource graph.
     * @return Copy of this graph.
     */
    fun whenUnchanged(graph: ResourceGraphDsl) = ResourceGraphDsl(
        resource = resource,
        whenSuccess = whenSuccess,
        whenChanged = whenChanged,
        whenUnchanged = graph,
        whenFailure = whenFailure
    )

    /**
     * Build DSL.
     *
     * @return Resource graph.
     */
    internal fun build(): ResourceGraph = ResourceGraph(
        resource = resource,
        whenSuccess = whenSuccess?.build(),
        whenChanged = whenChanged?.build(),
        whenUnchanged = whenUnchanged?.build(),
        whenFailure = whenFailure?.build()
    )
}
