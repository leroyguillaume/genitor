package tech.genitor.core

/**
 * Resource report.
 */
sealed class ResourceReport<D : ResourceReportData> {
    /**
     * Status.
     */
    interface Status

    /**
     * Failure report.
     *
     * @param cause Cause of failure.
     */
    data class Failure(
        override val resource: Resource,
        val cause: Throwable
    ) : ResourceReport<Nothing>() {
        /**
         * Status.
         */
        enum class Status : ResourceReport.Status {
            /**
             * Failed status.
             */
            Failed
        }

        override val status = Status.Failed
    }

    /**
     * Success report.
     *
     * @param status Status.
     * @param data Report data.
     */
    data class Success<D : ResourceReportData>(
        override val resource: Resource,
        override val status: Status,
        val data: D
    ) : ResourceReport<D>() {
        /**
         * Status.
         */
        enum class Status : ResourceReport.Status {
            /**
             * Changed status.
             */
            Changed,

            /**
             * Unchanged status.
             */
            Unchanged
        }
    }

    /**
     * Resource.
     */
    abstract val resource: Resource

    /**
     * Status.
     */
    abstract val status: Status
}

/**
 * Create changed resource report.
 *
 * @param data Resource report data.
 * @return Report.
 */
inline fun <reified D : ResourceReportData> Resource.changed(data: D) =
    ResourceReport.Success(this, ResourceReport.Success.Status.Changed, data)


/**
 * Create failure resource report.
 *
 * @param throwable Cause of failure.
 * @return Report.
 */
fun Resource.failure(throwable: Throwable) = ResourceReport.Failure(this, throwable)

/**
 * Create unchanged resource report.
 *
 * @param data Resource report data.
 * @return Report.
 */
inline fun <reified D : ResourceReportData> Resource.unchanged(data: D) =
    ResourceReport.Success(this, ResourceReport.Success.Status.Unchanged, data)
