package tech.genitor.core

/**
 * Catalog producer.
 */
interface CatalogProducer {
    /**
     * Send resource graphs to broker.
     *
     * @param node Node for which catalog was built.
     * @param catalog Catalog.
     */
    fun send(node: Node, catalog: Catalog)
}
