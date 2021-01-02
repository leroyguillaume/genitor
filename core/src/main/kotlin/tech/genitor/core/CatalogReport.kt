package tech.genitor.core

import java.time.Duration
import java.time.LocalDateTime

/**
 * Catalog execution report.
 *
 * @param executionStartDate Execution start date.
 * @param executionEndDate Execution end date.
 * @param executionDuration Duration of execution.
 * @param resourceReports Resource reports.
 */
data class CatalogReport(
    val executionStartDate: LocalDateTime,
    val executionEndDate: LocalDateTime,
    val executionDuration: Duration,
    val resourceReports: List<ResourceReport<*>>
)
