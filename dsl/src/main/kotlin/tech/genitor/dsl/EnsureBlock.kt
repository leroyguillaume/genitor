package tech.genitor.dsl

import tech.genitor.core.Facts

/**
 * Ensure block.
 *
 * @param fn Function to build resources.
 */
class EnsureBlock internal constructor(
    private val fn: EnsureBlock.(Facts) -> Unit
)
