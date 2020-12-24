package tech.genitor.resources.file

import tech.genitor.core.ResourceReportData
import java.nio.file.Path

/**
 * Symlink resource report data.
 *
 * @param path Path to symlink.
 * @param previousState Previous state of file.
 * @param target Path to symlink target.
 * @param previousTarget Previous path to symlink target.
 */
data class SymlinkResourceReportData(
    val path: Path,
    val previousState: FileState,
    val target: Path,
    val previousTarget: Path? = null
) : ResourceReportData
