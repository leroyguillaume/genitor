package tech.genitor.master.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.master.MasterProperties
import java.nio.file.Files
import kotlin.streams.toList

/**
 * Default implementation of DSL script entrypoint scanner.
 *
 * @param props Properties.
 */
@Component
class DefaultDslScriptEntrypointScanner(
    private val props: MasterProperties
) : DslScriptEntrypointScanner {
    private companion object {
        /**
         * Name of DSL entrypoint script file.
         */
        private const val EntrypointScriptFilename = "catalog.kts"

        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultDslScriptEntrypointScanner::class.java)
    }

    override fun scan() = Files.find(props.deployDir, 999, { path, attrs ->
        (attrs.isRegularFile && path.fileName.toString() == EntrypointScriptFilename).apply {
            if (Logger.isDebugEnabled) {
                val absolutePath = path.toAbsolutePath()
                if (this) {
                    Logger.debug("$absolutePath scanned as DSL script entrypoint")
                } else {
                    Logger.debug("$absolutePath ignored")
                }
            }
        }
    }).toList()
}
