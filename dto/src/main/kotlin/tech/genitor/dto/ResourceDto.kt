package tech.genitor.dto

import tech.genitor.core.Resource

/**
 * Resource DTO.
 *
 * @param type Type of resource.
 * @param params Resource parameters.
 */
data class ResourceDto(
    val type: Class<out Resource>,
    val params: Map<String, Any>
)
