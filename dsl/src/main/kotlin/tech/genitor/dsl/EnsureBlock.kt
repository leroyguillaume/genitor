package tech.genitor.dsl

import tech.genitor.core.Facts
import tech.genitor.core.Resource

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
    val resourceHolders get() = _resourceHolders.map { it.copy() }

    /**
     * Copy constructor.
     *
     * @param ensureBlock Ensure block to copy.
     */
    internal constructor(ensureBlock: EnsureBlock) : this(ensureBlock.fn) {
        _resourceHolders.addAll(ensureBlock.resourceHolders)
    }

    /**
     * Create resource holder.
     *
     * @param resource Resource.
     */
    fun add(resource: Resource) = ResourceHolder(resource).apply {
        _resourceHolders.add(this)
    }

    /**
     * Build resources from fact.
     *
     * This method returns a copy of this object applying function to build resource.
     *
     * @param facts Node facts.
     * @return Built ensure block.
     */
    fun build(facts: Facts) = EnsureBlock(this).apply { fn(facts) }
}
