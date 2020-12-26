package tech.genitor.dsl

import tech.genitor.core.CatalogBuilder
import tech.genitor.core.GenitorFile

/**
 * DSL compiler.
 */
interface DslCompiler {
    /**
     * Compile Kotlin DSL.
     *
     * @param genitorFile Project file.
     * @return Resource graphs builder for each node.
     * @throws DslException If DSL is invalid.
     */
    @Throws(DslException::class)
    fun compile(genitorFile: GenitorFile): List<CatalogBuilder>
}
