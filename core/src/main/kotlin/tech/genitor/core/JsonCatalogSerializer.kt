package tech.genitor.core

/**
 * JSON catalog serializer.
 */
interface JsonCatalogSerializer {
    /**
     * Serialize catalog to JSON format.
     *
     * @param catalog Catalog.
     * @return Serialized catalog.
     */
    fun serialize(catalog: Catalog): String
}
