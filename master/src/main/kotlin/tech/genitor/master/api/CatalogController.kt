package tech.genitor.master.api

import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import tech.genitor.master.catalog.ProjectScanner

/**
 * Catalog API.
 */
@RestController
@RequestMapping("/catalog")
class CatalogController(
    val projectScanner: ProjectScanner
) {
    /**
     * Compile and deploy catalog.
     */
    @PutMapping
    fun deploy() {
        projectScanner.scan()
    }
}
