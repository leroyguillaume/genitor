package tech.genitor.agent.catalog

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import tech.genitor.core.*
import java.time.Duration
import java.time.LocalDateTime

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
        val startDate = LocalDateTime.now()
        Logger.info("Starting execution of catalog")
        val resourceReports = catalog.graphs.flatMap { ensure(it) }
        val endDate = LocalDateTime.now()
        val duration = Duration.between(startDate, endDate)
        Logger.info("Catalog executed in ${duration.seconds} seconds")
        return CatalogReport(
            executionStartDate = startDate,
            executionEndDate = endDate,
            executionDuration = duration,
            resourceReports = resourceReports
        )
    }

    /**
     * Ensure resource.
     *
     * @param graph Resource graph.
     * @return Resource reports.
     */
    private fun ensure(graph: ResourceGraph?): List<ResourceReport<*>> = if (graph == null) {
        emptyList()
    } else {
        val report = graph.resource.ensure()
        listOf(report) + when (report) {
            is ResourceReport.Failure -> handleFailure(graph, report)
            is ResourceReport.Success -> handleSuccess(graph, report)
        }
    }

    /**
     * Handle changed status.
     *
     * @param graph Current resource graph node.
     * @return Resource reports.
     */
    private fun handleChanged(graph: ResourceGraph): List<ResourceReport<*>> {
        Logger.info("Resource '${graph.resource.name}' changed")
        return ensure(graph.whenChanged)
    }

    /**
     * Handle failure status.
     *
     * @param graph Current resource graph node.
     * @param report Resource report.
     * @return Resource reports.
     */
    private fun handleFailure(graph: ResourceGraph, report: ResourceReport.Failure): List<ResourceReport<*>> {
        Logger.error("Resource '${graph.resource.name}' failed", report.cause)
        return ensure(graph.whenFailure)
    }

    /**
     * Handle success resource report.
     *
     * @param graph Current resource graph node.
     * @param report Resource report.
     * @return Resource reports.
     */
    private fun handleSuccess(graph: ResourceGraph, report: ResourceReport.Success<*>): List<ResourceReport<*>> {
        val reports = when (report.status) {
            ResourceReport.Success.Status.Changed -> handleChanged(graph)
            ResourceReport.Success.Status.Unchanged -> handleUnchanged(graph)
        }
        return reports + ensure(graph.whenSuccess)
    }

    /**
     * Handle unchanged status.
     *
     * @param graph Current resource graph node.
     * @return Resource reports.
     */
    private fun handleUnchanged(graph: ResourceGraph): List<ResourceReport<*>> {
        Logger.info("Resource '${graph.resource.name}' unchanged")
        return ensure(graph.whenUnchanged)
    }
}
