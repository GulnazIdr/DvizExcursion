package org.example.project.core.network.ktor.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StepikUserDto(
    val users: List<UserDto>
)

@Serializable
data class UserDto(
    val id: Int,
    val details: String,
    @SerialName("short_bio")
    val shortBio: String,
    @SerialName("full_name")
    val fullName: String,
    val avatar: String,
)
