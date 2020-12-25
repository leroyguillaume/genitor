package tech.genitor.resources.file

import org.slf4j.LoggerFactory
import tech.genitor.core.ResourceReport
import tech.genitor.core.changed
import tech.genitor.core.failure
import tech.genitor.core.unchanged
import java.nio.file.Files
import java.nio.file.Path

/**
 * Symlink resource.
 */
data class SymlinkResource(
    override val params: Params
) : FileResource() {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(SymlinkResource::class.java)
    }

    /**
     * Parameters.
     *
     * @param path Path to symlink file.
     * @param target Path to file to link to.
     * @param force Force symlink creation if destination exists and it is regular file.
     */
    data class Params(
        override val path: Path,
        val target: Path,
        val force: Boolean = false
    ) : FileResourceParams

    /**
     * Ensure symlink is created and link targets the right file.
     *
     * @return Report.
     */
    override fun ensure() = try {
        doEnsure()
    } catch (exception: Exception) {
        Logger.debug("Unable to ensure symlink ${params.path}", exception)
        failure(exception)
    }

    /**
     * Ensure symlink is created and link targets the right file.
     *
     * @return Report.
     * @throws SymlinkDestinationAlreadyExists If force parameter is disabled and symlink destination already exists.
     */
    @Throws(SymlinkDestinationAlreadyExists::class)
    private fun doEnsure(): ResourceReport.Success<SymlinkResourceReportData> {
        val path = params.path.toAbsolutePath()
        val target = params.target.toAbsolutePath()
        val state = fileState(path)
        val reportData = SymlinkResourceReportData(
            path = path,
            previousState = state,
            target = target
        )
        return if (state == FileState.Absent) {
            updateSymlink(path, state, target)
            changed(reportData)
        } else if (state == FileState.Symlink) {
            val previousTarget = Files.readSymbolicLink(path)
            val reportDataWithPreviousTarget = reportData.copy(previousTarget = previousTarget)
            if (previousTarget == target) {
                Logger.debug("Symlink $path to $target is already up-to-date")
                unchanged(reportDataWithPreviousTarget)
            } else {
                updateSymlink(path, state, target)
                changed(reportDataWithPreviousTarget)
            }
        } else if (state == FileState.Regular && params.force) {
            updateSymlink(path, state, target)
            changed(reportData)
        } else {
            throw SymlinkDestinationAlreadyExists(path, state)
        }
    }

    /**
     * Update symlink.
     *
     * @param path Path to symlink.
     * @param state State of file.
     * @param target Path to target file to link to.
     */
    private fun updateSymlink(path: Path, state: FileState, target: Path) {
        if (Files.exists(path)) {
            Files.delete(path)
            Logger.debug("Existing ${state.label} $path deleted")
        }
        Files.createSymbolicLink(path, target)
        Logger.debug("Symlink $path to $target created")
        if (!Files.exists(target)) {
            Logger.warn("Target $target for symlink $path does not exist")
        }
    }
}
