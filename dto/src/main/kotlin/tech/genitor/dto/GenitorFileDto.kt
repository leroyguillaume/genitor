package tech.genitor.dto

import java.nio.file.Path

/**
 * Genitor project file DTO.
 *
 * @param name Project name.
 * @param namespace Project namespace.
 * @param manifestsDir Path to manifests directory relative to this file.
 * @param catalogFilename Name of catalog DSL script file.
 */
data class GenitorFileDto(
    val name: String,
    val namespace: String? = null,
    val manifestsDir: Path = Path.of("manifests"),
    val catalogFilename: String = "catalog.genitor.kts"
)
