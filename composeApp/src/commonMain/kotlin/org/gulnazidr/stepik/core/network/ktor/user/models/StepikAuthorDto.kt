package org.gulnazidr.stepik.core.network.ktor.user.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StepikAuthorDto (
    val users: List<AuthorDto>
)

@Serializable
data class AuthorDto(
    val id: Int,
    @SerialName("full_name")
    val fullName: String,
    val avatar: String,
)