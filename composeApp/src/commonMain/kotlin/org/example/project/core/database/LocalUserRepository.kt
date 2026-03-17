package org.example.project.core.database

import io.github.aakira.napier.Napier
import org.example.project.core.database.dao.UserDao
import org.example.project.core.database.mappers.toUser
import org.example.project.core.database.mappers.toUserEntity
import org.example.project.core.model.User
import org.example.project.feature.onboarding.domain.DataStoreRepository
import kotlin.compareTo
import kotlin.text.compareTo

class LocalUserRepository(
    private val userDao: UserDao,
    private val dataStoreRepository: DataStoreRepository
) {
    suspend fun saveUser(user: User): Boolean{
        return try {
            userDao.setUser(user.toUserEntity())
            dataStoreRepository.setCurrentUserId(userDao.getUser().id)
            true
        }catch (e: Exception){
            Napier.e("saving user error: $e")
            false
        }
    }

    suspend fun updateUser(user: User): Boolean{
        return try {
            val rowsUpdated = userDao.updateUser(user.toUserEntity())
            rowsUpdated > 0
        }catch (e: Exception){
            Napier.e("updating user error $e")
            false
        }
    }

    suspend fun getUser(): User?{
        return try {
            userDao.getUser().toUser()
        }catch (e: Exception){
            Napier.e("getting user by id error:x $e")
            null
        }
    }

    suspend fun deleteUser(): Boolean{
        return try {
            userDao.deleteUser()
            true
        }catch (e: Exception){
            Napier.e("getting user by id error:x $e")
            false
        }
    }
}