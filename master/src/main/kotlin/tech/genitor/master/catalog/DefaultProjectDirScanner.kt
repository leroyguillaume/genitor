package tech.genitor.master.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.core.Project
import tech.genitor.core.ProjectDir
import tech.genitor.core.ProjectDirScanner
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

/**
 * Default implementation of Genitor project scanner.
 */
@Component
class DefaultProjectDirScanner : ProjectDirScanner {
    private companion object {
        /**
         * Name of DSL entrypoint script file.
         */
        private const val EntrypointScriptFilename = "catalog.genitor.kts"

        /**
         * Manifests directory name.
         */
        private const val ManifestsDirName = "manifests"

        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultProjectDirScanner::class.java)
    }

    override fun scan(dir: Path) = Files.list(dir)
        .filter { Files.isDirectory(it) }
        .toList()
        .flatMap { scan(it, Project.RootNamespace) }

    /**
     * Scan recursively directory and list projects.
     *
     * @param path Path.
     * @param namespace Namespace.
     * @return Projects.
     */
    private fun scan(path: Path, namespace: String): List<ProjectDir> {
        val absolutePath = path.toAbsolutePath()
        Logger.debug("Scanning $absolutePath")
        val manifestsDir = path.resolve(ManifestsDirName)
        val entrypointScriptPath = manifestsDir.resolve(EntrypointScriptFilename)
        return if (Files.isDirectory(manifestsDir) && Files.isRegularFile(entrypointScriptPath)) {
            Logger.debug("$absolutePath scanned as project directory")
            listOf(
                ProjectDir(
                    project = Project(
                        name = path.fileName.toString(),
                        namespace = namespace,
                    ),
                    path = path,
                    entrypointScriptPath = entrypointScriptPath
                )
            )
        } else {
            Files.list(path)
                .filter { Files.isDirectory(it) }
                .toList()
                .flatMap { scan(it, "$namespace${Project.NamespaceSeparator}${path.fileName}") }
        }
    }
}
