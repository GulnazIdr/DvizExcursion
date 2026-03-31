package org.gulnazidr.stepik.core.model

data class StepikCatalog(
    val pageInfo: PageInfo,
    val catalogs: List<Catalog>
)
