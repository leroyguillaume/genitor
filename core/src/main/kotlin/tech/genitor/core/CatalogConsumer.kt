package tech.genitor.core

/**
 * Catalog consumer.
 */
interface CatalogConsumer {
    /**
     * Consume catalog in JSON format.
     *
     * @param catalogJson Catalog in JSON format.
     * @param hostname Hostname of node targeted by catalog.
     */
    fun consume(catalogJson: String, hostname: String)
}
