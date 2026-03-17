package org.example.project.core.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

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
