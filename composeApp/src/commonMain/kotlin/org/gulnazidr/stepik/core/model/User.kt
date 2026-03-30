package org.gulnazidr.stepik.core.model

data class User(
    val id: Int = 0,
    val name: String = "",
    val details: String = "",
    val shortBio: String = "",
    val profileImg: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = ""
)