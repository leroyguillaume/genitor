package tech.genitor.dto

import tech.genitor.core.GenitorFile
import java.nio.file.Path

/**
 * Genitor project file DTO.
 *
 * @param manifestsDir Relative path to manifests directory.
 * @param catalogScriptFilename Catalog script filename.
 */
data class GenitorFileDto(
    val manifestsDir: Path = GenitorFile.DefaultManifestsDir,
    val catalogScriptFilename: String = GenitorFile.DefaultCatalogScriptFilename
)
