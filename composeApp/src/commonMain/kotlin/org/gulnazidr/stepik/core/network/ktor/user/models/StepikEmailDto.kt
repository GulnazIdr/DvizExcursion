package org.gulnazidr.stepik.core.network.ktor.user.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.gulnazidr.stepik.core.network.ktor.models.MetaDto

@Serializable
data class StepikEmailDto(
    val meta: MetaDto,
    @SerialName("email-addresses")
    val emailAddress: List<EmailDto>
)

@Serializable
data class EmailDto(
    val id: Int,
    val email: String
)