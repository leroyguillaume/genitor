package tech.genitor.dto

/**
 * Project DTO.
 *
 * @param name Name.
 * @param namespace Namespace.
 */
data class ProjectDto(
    val name: String,
    val namespace: String? = null
)
