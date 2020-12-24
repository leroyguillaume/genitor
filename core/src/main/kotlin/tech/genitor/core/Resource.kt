package tech.genitor.core

/**
 * Resource.
 */
interface Resource {
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
