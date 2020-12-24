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
inline fun <reified D : ResourceReportData> changed(data: D) =
    ResourceReport.Success(ResourceReport.Success.Status.Changed, data)


/**
 * Create failure resource report.
 *
 * @param throwable Cause of failure.
 * @return Report.
 */
fun failure(throwable: Throwable) = ResourceReport.Failure(throwable)

/**
 * Create unchanged resource report.
 *
 * @param data Resource report data.
 * @return Report.
 */
inline fun <reified D : ResourceReportData> unchanged(data: D) =
    ResourceReport.Success(ResourceReport.Success.Status.Unchanged, data)
