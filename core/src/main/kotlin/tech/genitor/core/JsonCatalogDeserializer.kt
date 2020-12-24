package tech.genitor.core

/**
 * JSON catalog deserializer.
 */
interface JsonCatalogDeserializer {
    /**
     * Deserialize catalog from JSON.
     *
     * @param catalogJson Catalog in JSON format.
     * @return Catalog.
     * @throws ResourceInstantiationException If resource instantiation failed.
     */
    @Throws(ResourceInstantiationException::class)
    fun deserialize(catalogJson: String): Catalog
}
