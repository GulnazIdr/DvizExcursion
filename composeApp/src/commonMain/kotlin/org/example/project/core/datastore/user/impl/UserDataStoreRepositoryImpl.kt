package org.example.project.core.datastore.user.impl

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import org.example.project.core.datastore.user.DataStoreUserSerial
import org.example.project.core.datastore.user.source.UserDataStoreRepository
import org.example.project.core.model.User

class UserDataStoreRepositoryImpl(
    private val userDataStore: DataStore<DataStoreUserSerial>
): UserDataStoreRepository {

    override suspend fun saveCurrentUser(user: User) {
        userDataStore.updateData { credentials ->
            credentials.copy(
                name = user.name,
                details = user.details,
                shortBio = user.shortBio,
                profileImg = user.profileImg,
                email = user.email,
                phone = user.phone,
                password = user.password
            )
        }
    }

    override suspend fun getCurrentUser(): User {
        return userDataStore.data.first().toUser()
    }

    override suspend fun deleteUser() {
        userDataStore.updateData { credentials ->
            credentials.copy(
                id = 0,
                name = "",
                details = "",
                shortBio = "",
                profileImg = "",
                email = "",
                phone = "",
                password = ""
            )
        }
    }
}

private fun DataStoreUserSerial.toUser(): User{
    return User(
        id = id,
        name = name,
        details = details,
        shortBio = shortBio,
        profileImg = profileImg,
        email = email,
        phone = phone,
        password = password
    )
}