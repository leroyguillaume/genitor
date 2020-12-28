package tech.genitor.master.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.core.GenitorFile
import tech.genitor.core.GenitorFileLoader
import tech.genitor.core.GenitorFileScanner
import java.nio.file.Files
import java.nio.file.Path

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

    override fun scan(dir: Path): GenitorFile {
        val dirAbsolutePath = dir.toAbsolutePath()
        val genitorFilePath = dirAbsolutePath.resolve(GenitorFilename)
        val defaultGenitorFile = GenitorFile(
            manifestsDir = dir.resolve(GenitorFile.DefaultManifestsDir),
            catalogScriptFilename = GenitorFile.DefaultCatalogScriptFilename
        )
        return if (!Files.exists(genitorFilePath)) {
            Logger.debug("No Genitor project file found, default one will be used ($genitorFilePath)")
            defaultGenitorFile
        } else if (!Files.isRegularFile(genitorFilePath)) {
            Logger.debug("Genitor project file is not a regular file, default one will be used ($genitorFilePath)")
            defaultGenitorFile
        } else {
            Logger.debug("Loading Genitor project file ($genitorFilePath)")
            genitorFileLoader.load(genitorFilePath).apply {
                Logger.debug("Genitor project file loaded ($genitorFilePath)")
            }
        }
    }
}
