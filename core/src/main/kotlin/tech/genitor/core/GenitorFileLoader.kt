package tech.genitor.core

import java.nio.file.Path

/**
 * Genitor project file loader.
 */
interface GenitorFileLoader {
    /**
     * Load Genitor project file.
     *
     * @param genitorFilePath Path to Genitor project file.
     * @return Genitor project file.
     * @throws GenitorFileException If an error occurs during loading.
     */
    @Throws(GenitorFileException::class)
    fun load(genitorFilePath: Path): GenitorFile
}
