package tech.genitor.resources.file

import tech.genitor.core.Resource

/**
 * File resource.
 */
abstract class FileResource : Resource {
    override val name by lazy { params.path.toAbsolutePath().toString() }

    abstract override val params: FileResourceParams
}
