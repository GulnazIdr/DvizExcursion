package org.example.project.core.database.impl

import io.github.aakira.napier.Napier
import org.example.project.core.database.source.LocalUserRepository
import org.example.project.core.datastore.user.source.UserDataStoreRepository
import org.example.project.core.model.User

class LocalUserRepositoryImpl(
    private val userDataStoreRepository: UserDataStoreRepository
): LocalUserRepository {
    override suspend fun saveUser(user: User): Boolean{
        return try {
            userDataStoreRepository.saveCurrentUser(user)
            true
        }catch (e: Exception){
            Napier.e("saving user error: $e")
            false
        }
    }

    override suspend fun updateUser(user: User): Boolean{
        return try {
            userDataStoreRepository.saveCurrentUser(user)
            true
        }catch (e: Exception){
            Napier.e("updating user error $e")
            false
        }
    }

    override suspend fun getUser(): User?{
        return try {
           userDataStoreRepository.getCurrentUser()
        }catch (e: Exception){
            Napier.e("getting user by id error:x $e")
            null
        }
    }

    override suspend fun deleteUser(): Boolean{
        return try {
            userDataStoreRepository.deleteUser()
            true
        }catch (e: Exception){
            Napier.e("getting user by id error:x $e")
            false
        }
    }
}