package tech.genitor.dsl

/**
 * Create catalog block.
 *
 * @param block Function to build catalog.
 * @return Catalog block.
 */
fun catalog(block: CatalogBlock.() -> Unit) = CatalogBlock().apply(block)
