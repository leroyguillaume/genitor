package tech.genitor.core

/**
 * Catalog execution report.
 *
 * @param resourceReports Resource reports.
 */
data class CatalogReport(
    val resourceReports: List<ResourceReport<*>>
)
