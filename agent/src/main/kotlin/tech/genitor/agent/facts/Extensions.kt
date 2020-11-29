package tech.genitor.agent.facts

import tech.genitor.core.Facts
import tech.genitor.dto.FactsDto

/**
 * Convert facts to DTO.
 */
fun Facts.toDto() = FactsDto(
    agentVersion = agentVersion
)
