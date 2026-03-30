package org.gulnazidr.stepik.core.network.ktor.catalog.model

import kotlinx.serialization.Serializable
import org.gulnazidr.stepik.core.network.ktor.models.MetaDto

@Serializable
data class StepikCatalogDto(
    val meta: MetaDto,
    val catalogs: List<CatalogDto>
)