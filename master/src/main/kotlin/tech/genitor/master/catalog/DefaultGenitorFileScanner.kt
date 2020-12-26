package tech.genitor.master.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.core.GenitorFile
import tech.genitor.core.GenitorFileException
import tech.genitor.core.GenitorFileLoader
import tech.genitor.core.GenitorFileScanner
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

/**
 * Default implementation of Genitor project scanner.
 *
 * @param genitorFileLoader Genitor project file loader.
 */
@Component
class DefaultGenitorFileScanner(
    private val genitorFileLoader: GenitorFileLoader
) : GenitorFileScanner {
    private companion object {
        /**
         * Name of Genitor project file.
         */
        private const val GenitorFilename = "genitor.json"

        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultGenitorFileScanner::class.java)
    }

    override fun scan(dir: Path) = doScan(dir)

    /**
     * Scan recursively directory and list Genitor project files.
     *
     * @param path Path.
     * @return Genitor project files.
     */
    private fun doScan(path: Path): List<GenitorFile> {
        val absolutePath = path.toAbsolutePath()
        Logger.debug("Scanning $absolutePath")
        val genitorFilePath = absolutePath.resolve(GenitorFilename)
        return if (!Files.exists(genitorFilePath)) {
            Files.list(path)
                .filter { Files.isDirectory(it) }
                .toList()
                .flatMap { doScan(it) }
        } else {
            if (!Files.isRegularFile(genitorFilePath)) {
                Logger.warn("File $genitorFilePath ignored because it is not regular file")
                emptyList()
            } else {
                try {
                    val genitorFile = genitorFileLoader.load(genitorFilePath)
                    Logger.debug("File $genitorFilePath loaded as Genitor project file")
                    listOf(genitorFile)
                } catch (exception: GenitorFileException) {
                    Logger.error(exception.message, exception)
                    emptyList()
                }
            }
        }
    }
}
