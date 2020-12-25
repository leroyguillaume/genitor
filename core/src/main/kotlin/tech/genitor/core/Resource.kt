package tech.genitor.core

/**
 * Resource.
 */
interface Resource {
    /**
     * Name.
     */
    val name: String

    /**
     * Parameters.
     */
    val params: ResourceParams

    /**
     * Ensure resource.
     *
     * @return Report.
     */
    fun ensure(): ResourceReport<*>
}
