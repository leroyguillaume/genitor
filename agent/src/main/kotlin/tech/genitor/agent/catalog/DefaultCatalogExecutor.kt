package tech.genitor.agent.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.core.Catalog
import tech.genitor.core.CatalogExecutor
import tech.genitor.core.CatalogReport
import tech.genitor.core.ResourceReport

/**
 * Default implementation of catalog executor.
 */
@Component
class DefaultCatalogExecutor : CatalogExecutor {
    private companion object {
        /**
         * Logger.
         */
        private val Logger = LoggerFactory.getLogger(DefaultCatalogExecutor::class.java)
    }

    override fun execute(catalog: Catalog): CatalogReport {
        Logger.debug("Starting execution of catalog")
        val resourceReports = catalog.graphs.map { graph ->
            graph.resource.ensure().apply {
                when (this) {
                    is ResourceReport.Failure -> handleFailure(this)
                    is ResourceReport.Success -> handleSuccess(this)
                }
            }
        }
        return CatalogReport(
            resourceReports = resourceReports
        )
    }

    /**
     * Handle changed status.
     *
     * @param report Resource report.
     */
    private fun handleChanged(report: ResourceReport.Success<*>) {
        Logger.info("Resource '${report.resource.name}' changed")
    }

    /**
     * Handle failure status.
     *
     * @param report Resource report.
     */
    private fun handleFailure(report: ResourceReport.Failure) {
        Logger.error("Resource '${report.resource.name}' failed", report.cause)
    }

    /**
     * Handle success resource report.
     *
     * @param report Resource report.
     */
    private fun handleSuccess(report: ResourceReport.Success<*>) = when (report.status) {
        ResourceReport.Success.Status.Changed -> handleChanged(report)
        ResourceReport.Success.Status.Unchanged -> handleUnchanged(report)
    }

    /**
     * Handle unchanged status.
     *
     * @param report Resource report.
     */
    private fun handleUnchanged(report: ResourceReport.Success<*>) {
        Logger.info("Resource '${report.resource.name}' changed")
    }
}
