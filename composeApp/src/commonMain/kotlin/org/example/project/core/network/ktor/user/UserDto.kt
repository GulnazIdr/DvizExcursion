package org.example.project.core.network.ktor.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.project.core.network.ktor.models.MetaDto

@Serializable
data class StepikUserDto(
    val profiles: List<UserDto>
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
    @SerialName("email_addresses")
    val emailAddresses: List<Int>
)

