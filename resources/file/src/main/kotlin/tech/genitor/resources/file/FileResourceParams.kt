package tech.genitor.resources.file

import tech.genitor.core.ResourceParams
import java.nio.file.Path

/**
 * File resource parameters.
 */
interface FileResourceParams : ResourceParams {
    /**
     * Path to file.
     */
    val path: Path
}
