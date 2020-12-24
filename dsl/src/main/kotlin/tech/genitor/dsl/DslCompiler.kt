package tech.genitor.dsl

import tech.genitor.core.CatalogBuilder
import java.nio.file.Path

/**
 * DSL compiler.
 */
interface DslCompiler {
    /**
     * Compile Kotlin DSL.
     *
     * @param scriptPath Path to script file.
     * @return Resource graphs builder for each node.
     * @throws DslException If DSL is invalid.
     */
    @Throws(DslException::class)
    fun compile(scriptPath: Path): List<CatalogBuilder>
}
