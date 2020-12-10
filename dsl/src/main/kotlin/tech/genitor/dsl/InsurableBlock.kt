package tech.genitor.dsl

import tech.genitor.core.Facts

/**
 * Block which can contain ensure block.
 */
abstract class InsurableBlock {
    /**
     * Ensure block.
     */
    private lateinit var _ensureBlock: EnsureBlock

    /**
     * Ensure block.
     */
    internal val ensureBlock get() = if (this::_ensureBlock.isInitialized) EnsureBlock(_ensureBlock) else null

    /**
     * Create ensure block.
     *
     * @param block Function to build resources.
     * @throws EnsureBlockAlreadyDefinedException If ensure block is already defined.
     */
    @Throws(EnsureBlockAlreadyDefinedException::class)
    fun ensure(block: EnsureBlock.(Facts) -> Unit) {
        if (this::_ensureBlock.isInitialized) {
            throw EnsureBlockAlreadyDefinedException()
        }
        _ensureBlock = EnsureBlock(block)
    }

    /**
     * Set ensure block.
     *
     * This method modifies this block. It is not thread safe. Please use with caution. It should be used only for
     * copy constructor.
     *
     * @param ensureBlock Ensure block.
     */
    protected fun setEnsureBlock(ensureBlock: EnsureBlock) {
        _ensureBlock = ensureBlock
    }
}
