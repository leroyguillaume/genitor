package tech.genitor.core

/**
 * JSON resource parameters serializer.
 */
interface JsonResourceParamsSerializer {
    /**
     * Serialize resource parameters to JSON format.
     *
     * @param params Parameters.
     * @return Serialized parameters.
     */
    fun serialize(params: ResourceParams): String
}
