package org.gulnazidr.stepik.core.network.ktor.catalog.model

import kotlinx.serialization.Serializable

@Serializable
data class CatalogContentDto(
    val id: Int,
    val title: String,
    val description: String,
    val courses: List<Int>
)
