package tech.genitor.master.catalog

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import tech.genitor.core.GenitorFile
import tech.genitor.core.GenitorFileException
import tech.genitor.core.GenitorFileLoader
import tech.genitor.core.ProjectMetadata
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
    override fun load(genitorFilePath: Path): GenitorFile {
        val genitorFileDto = try {
            Files.newBufferedReader(genitorFilePath).use { mapper.readValue<GenitorFileDto>(it) }
        } catch (exception: Exception) {
            throw GenitorFileException.Parsing(genitorFilePath, exception)
        }
        check(genitorFilePath, genitorFileDto)
        return genitorFileDto.toGenitorFile(genitorFilePath)
    }

    /**
     * Check Genitor project file is valid.
     *
     * @param genitorFilePath Path to Genitor project file.
     * @param genitorFileDto Genitor project file DTO.
     */
    private fun check(genitorFilePath: Path, genitorFileDto: GenitorFileDto) {
        val namespace = genitorFileDto.namespace
        if (namespace != null && !ProjectMetadata.NamespaceRegex.matches(namespace)) {
            throw GenitorFileException.Validation(
                path = genitorFilePath,
                message = "Invalid namespace '$namespace' (should match ${ProjectMetadata.NamespaceRegex.pattern})"
            )
        }
    }

    /**
     * Convert DTO to Genitor project file.
     *
     * @param path Path to Genitor project file.
     * @return Genitor project file.
     */
    private fun GenitorFileDto.toGenitorFile(path: Path): GenitorFile {
        val rootDir = path.parent
        return GenitorFile(
            projectMetadata = ProjectMetadata(
                name = name,
                namespace = namespace
            ),
            rootDir = rootDir,
            catalogPath = rootDir.resolve(manifestsDir).resolve(catalogFilename)
        )
    }
}
