package tech.genitor.core

/**
 * Catalog producer.
 */
interface CatalogProducer {
    /**
     * Send resource graphs to broker.
     *
     * @param catalog Catalog.
     */
    fun send(catalog: Catalog)
}
