package tech.genitor.dsl

import org.junit.jupiter.api.Test
import java.nio.file.Paths

internal class DefaultDslCompilerTest {
    private val compiler = DefaultDslCompiler()

    @Test
    fun inventory() {
        val scriptPath = Paths.get(javaClass.getResource("/scripts/inventory.kts").toURI())
        val builders = compiler.compile(scriptPath)
        TODO()
    }
}
