package tech.genitor.agent.api.facts

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.genitor.agent.facts.toDto
import tech.genitor.core.FactsCollector

/**
 * Facts API.
 *
 * @param factsCollector Facts collector.
 */
@RestController
@RequestMapping("/facts")
class FactsController(
    private val factsCollector: FactsCollector
) {
    @GetMapping
    fun collectFacts() = factsCollector.collect().toDto()
}
