package org.example.project.core.network.ktor.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StepikCurrentProfileDto(
    val profiles: List<ProfileDto>
)

@Serializable
data class ProfileDto(
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

