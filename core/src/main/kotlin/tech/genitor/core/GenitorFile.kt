package tech.genitor.core

import java.nio.file.Path
import java.nio.file.Paths

/**
 * Genitor project file.
 *
 * @param manifestsDir Path to manifests directory.
 * @param catalogScriptFilename Catalog script filename.
 */
data class GenitorFile(
    val manifestsDir: Path,
    val catalogScriptFilename: String
) {
    companion object {
        /**
         * Default catalog script filename.
         */
        const val DefaultCatalogScriptFilename = "catalog.genitor.kts"

        /**
         * Default relative path to manifests directory.
         */
        val DefaultManifestsDir = Paths.get("manifests")
    }
}
