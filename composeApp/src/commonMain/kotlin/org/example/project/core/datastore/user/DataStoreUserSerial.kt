package org.example.project.core.datastore.user

import kotlinx.serialization.Serializable

@Serializable
data class DataStoreUserSerial(
    val id: Int = 0,
    val name: String = "",
    val details: String= "",
    val shortBio: String= "",
    val profileImg: String= "",
    val email: String= "",
    val phone: String = "",
    val password: String = ""
)
