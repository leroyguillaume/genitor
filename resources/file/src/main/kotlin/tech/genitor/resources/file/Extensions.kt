package tech.genitor.resources.file

import tech.genitor.dsl.EnsureBlock
import tech.genitor.dsl.ResourceGraphDsl
import java.nio.file.Path

/**
 * Add symlink resource.
 *
 * @param path Path to symlink file.
 * @param target Path to file to link to.
 * @param force Force symlink creation if destination exists and it is regular file.
 * @return Resource graph.
 */
fun EnsureBlock.symlink(
    path: Path,
    target: Path,
    force: Boolean = false
) = ResourceGraphDsl(
    resource = SymlinkResource(
        params = SymlinkResource.Params(
            path = path,
            target = target,
            force = force
        )
    )
)
