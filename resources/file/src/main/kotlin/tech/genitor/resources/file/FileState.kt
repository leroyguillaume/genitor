package tech.genitor.resources.file

import java.nio.file.Files
import java.nio.file.Path

/**
 * File state.
 *
 * @param label Label used by logger.
 */
enum class FileState(
    val label: String
) {
    /**
     * Absent.
     */
    Absent("absent"),

    /**
     * Directory.
     */
    Directory("directory"),

    /**
     * Regular.
     */
    Regular("regular file"),

    /**
     * Symlink.
     */
    Symlink("symlink"),

    /**
     * Unknown.
     */
    Unknown("unknown file type")
}

/**
 * Determine file state.
 *
 * @param path Path to file.
 * @return File state.
 */
fun fileState(path: Path) = when {
    Files.isDirectory(path) -> FileState.Directory
    Files.isRegularFile(path) -> FileState.Regular
    Files.isSymbolicLink(path) -> FileState.Symlink
    !Files.exists(path) -> FileState.Absent
    else -> FileState.Unknown
}
