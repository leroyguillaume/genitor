package tech.genitor.resources.file

import java.nio.file.Path

/**
 * Exception thrown when try to override file by symlink.
 *
 * @param path Path to file.
 * @param fileState File state.
 */
class SymlinkDestinationAlreadyExists(
    val path: Path,
    val fileState: FileState
) : RuntimeException() {
    override val message = "Destination of symlink $path already exists: it is ${fileState.label}"
}
