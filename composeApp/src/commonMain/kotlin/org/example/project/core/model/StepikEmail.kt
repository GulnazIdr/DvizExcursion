package org.example.project.core.model

data class StepikEmail(
    val pageInfo: PageInfo,
    val emails: List<Email>
)

data class Email(
    val id: Int,
    val email: String
)
