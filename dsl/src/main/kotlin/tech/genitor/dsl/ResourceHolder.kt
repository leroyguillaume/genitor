package tech.genitor.dsl

import tech.genitor.core.Resource

/**
 * Resource holder.
 *
 * @param resource Resource.
 */
class ResourceHolder(
    val resource: Resource
) {
    /**
     * Copy constructor.
     *
     * @param resourceHolder Resource holder.
     */
    constructor(resourceHolder: ResourceHolder) : this(resourceHolder.resource)
}
