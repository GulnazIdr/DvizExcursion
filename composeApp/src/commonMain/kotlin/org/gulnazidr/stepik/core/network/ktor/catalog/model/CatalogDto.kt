package org.gulnazidr.stepik.core.network.ktor.catalog.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDto(
    val id: Int,
    val title: String,
    val content: List<CatalogContentDto>
)