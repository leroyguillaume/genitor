package tech.genitor.master.catalog

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import tech.genitor.core.GenitorFile
import tech.genitor.core.GenitorFileException
import tech.genitor.core.GenitorFileLoader
import tech.genitor.dto.GenitorFileDto
import java.nio.file.Files
import java.nio.file.Path

/**
 * Default implementation of Genitor project file loader.
 *
 * @param mapper Object mapper.
 */
@Component
class DefaultGenitorFileLoader(
    private val mapper: ObjectMapper
) : GenitorFileLoader {
    private companion object {
        /**
         * Filename regex.
         */
        private val FilenameRegex = Regex("^[A-Za-z0-9.-_]+$")
    }

    override fun load(genitorFilePath: Path): GenitorFile {
        val genitorFileDto = try {
            Files.newBufferedReader(genitorFilePath).use { mapper.readValue<GenitorFileDto>(it) }
        } catch (exception: Exception) {
            throw GenitorFileException.Parsing(genitorFilePath, exception)
        }
        val rootDir = genitorFilePath.parent
        val manifestsDir = rootDir.resolve(genitorFileDto.manifestsDir)
        if (!Files.isDirectory(manifestsDir)) {
            throw GenitorFileException.Validation(genitorFilePath, "$manifestsDir is not a directory")
        }
        if (!FilenameRegex.matches(genitorFileDto.catalogScriptFilename)) {
            throw GenitorFileException.Validation(
                path = genitorFilePath,
                message = "invalid catalog script filename (should match ${FilenameRegex.pattern})"
            )
        }
        return GenitorFile(
            manifestsDir = manifestsDir,
            catalogScriptFilename = genitorFileDto.catalogScriptFilename
        )
    }
}
