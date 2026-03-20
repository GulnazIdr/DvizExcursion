package org.example.project.core.network.ktor.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    val id: Int,
    val title: String,
    val cover: String? = null,
    val summary: String,
    val price: String? = null,
    val learners_count: Int,
    val certificate_link: String?,
)

//   "currency_code": "RUB",
//     "title_en": "Futures trading",
//      "language": "ru",
