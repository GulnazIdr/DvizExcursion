package org.gulnazidr.stepik.core.model

data class Catalog(
    val id: Int,
    val title: String,
    val content: List<CatalogContent>
)

data class CatalogContent(
    val id: Int,
    val title: String,
    val description: String,
    val courses: List<CourseDetail>
)
