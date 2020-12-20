package tech.genitor.master.api

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.genitor.master.catalog.CatalogService

/**
 * Catalog API.
 *
 * @param catalogService Catalog service.
 */
@RestController
@RequestMapping("/catalog")
class CatalogController(
    private val catalogService: CatalogService
) {
    /**
     * Compile and deploy catalog.
     */
    @PutMapping
    fun deploy() {
        catalogService.deploy()
    }
}
