package tech.genitor.dsl

import tech.genitor.core.Facts

/**
 * Block which can contain ensure block.
 */
abstract class InsurableBlock {
    /**
     * Ensure block.
     */
    private lateinit var ensureBlock: EnsureBlock

    /**
     * Create ensure block.
     *
     * @param block Function to build resources.
     * @throws EnsureBlockAlreadyDefinedException If ensure block is already defined.
     */
    @Throws(EnsureBlockAlreadyDefinedException::class)
    fun ensure(block: EnsureBlock.(Facts) -> Unit) {
        if (this::ensureBlock.isInitialized) {
            throw EnsureBlockAlreadyDefinedException("Ensure block is already defined for this catalog!")
        }
        ensureBlock = EnsureBlock(block)
    }
}
