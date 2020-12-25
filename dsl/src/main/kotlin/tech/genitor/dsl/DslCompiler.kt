package tech.genitor.dsl

import tech.genitor.core.CatalogBuilder
import tech.genitor.core.ProjectDir

/**
 * DSL compiler.
 */
interface DslCompiler {
    /**
     * Compile Kotlin DSL.
     *
     * @param projectDir Project directory.
     * @return Resource graphs builder for each node.
     * @throws DslException If DSL is invalid.
     */
    @Throws(DslException::class)
    fun compile(projectDir: ProjectDir): List<CatalogBuilder>
}
