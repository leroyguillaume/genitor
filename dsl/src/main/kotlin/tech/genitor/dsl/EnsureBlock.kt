package tech.genitor.dsl

import tech.genitor.core.Facts

/**
 * Ensure block.
 *
 * @param fn Function to build resources.
 */
class EnsureBlock internal constructor(
    private val fn: EnsureBlock.(Facts) -> Unit
) {
    /**
     * Resource holders.
     */
    private val _resourceHolders = mutableListOf<ResourceHolder>()

    /**
     * Resource holders.
     */
    internal val resourceHolders = _resourceHolders.map { ResourceHolder(it) }

    /**
     * Copy constructor.
     *
     * @param ensureBlock Ensure block to copy.
     */
    internal constructor(ensureBlock: EnsureBlock) : this(ensureBlock.fn)

    /**
     * Add resource holder.
     *
     * @param resourceHolder Resource holder.
     */
    fun add(resourceHolder: ResourceHolder) {
        _resourceHolders.add(resourceHolder)
    }

    /**
     * Build resources from fact.
     *
     * This method returns a copy of this object applying function to build resource.
     *
     * @param facts Node facts.
     * @return Built ensure block.
     */
    internal fun build(facts: Facts) = EnsureBlock(this).apply { fn(facts) }
}
