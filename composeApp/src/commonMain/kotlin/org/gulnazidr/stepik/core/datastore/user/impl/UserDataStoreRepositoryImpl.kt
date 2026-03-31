package org.gulnazidr.stepik.core.datastore.user.impl

import androidx.datastore.core.DataStore
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.first
import org.gulnazidr.stepik.core.datastore.user.DataStoreUserSerial
import org.gulnazidr.stepik.core.datastore.user.source.UserDataStoreRepository
import org.gulnazidr.stepik.core.domain.cancellationRunCatching
import org.gulnazidr.stepik.core.model.User

class UserDataStoreRepositoryImpl(
    private val userDataStore: DataStore<DataStoreUserSerial>
): UserDataStoreRepository {

    override suspend fun saveCurrentUser(user: User): Result<Boolean> {
        return cancellationRunCatching {
            val savedUser = userDataStore.updateData { credentials ->
                credentials.copy(
                    id = user.id,
                    name = user.name,
                    details = user.details,
                    shortBio = user.shortBio,
                    profileImg = user.profileImg,
                    email = user.email,
                    phone = user.phone,
                    password = user.password
                )
            }
            areUsersEqual(user, savedUser.toUser())
        }.onFailure { error ->
            Napier.e("saving current user to datastore: ${error.message}")
        }
    }

    override suspend fun getCurrentUser(): Result<User> {
        return cancellationRunCatching {
            val user = userDataStore.data.first().toUser()
            Napier.wtf("user3 $user")
            user
        }.onFailure { error ->
            Napier.e("getting current user from datastore: ${error.message}")
        }
    }

    override suspend fun deleteUser(): Result<Boolean> {
        return cancellationRunCatching {
            val user = userDataStore.updateData { credentials ->
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
            user.id == 0
        }.onFailure { error ->
            Napier.e("deleting current user from datastore: ${error.message}")
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

private fun areUsersEqual(user1: User, user2: User): Boolean {
    return user1.id == user2.id &&
            user1.name == user2.name &&
            user1.email == user2.email &&
            user1.profileImg == user2.profileImg &&
            user1.details == user2.details &&
            user1.shortBio == user2.shortBio &&
            user1.phone == user2.phone &&
            user1.password == user2.password
}