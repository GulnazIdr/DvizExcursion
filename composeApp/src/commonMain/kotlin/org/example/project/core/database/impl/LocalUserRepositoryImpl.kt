package org.example.project.core.database.impl

import io.github.aakira.napier.Napier
import org.example.project.core.database.dao.UserDao
import org.example.project.core.database.model.CourseEntity
import org.example.project.core.database.model.UserEntity
import org.example.project.core.database.source.LocalUserRepository
import org.example.project.core.model.Course
import org.example.project.core.model.CourseDetail
import org.example.project.core.model.PageInfo
import org.example.project.core.model.Stepik
import org.example.project.core.model.StepikDetailed
import org.example.project.core.model.User
import org.example.project.feature.onboarding.domain.DataStoreRepository

class LocalUserRepositoryImpl(
    private val userDao: UserDao,
    private val dataStoreRepository: DataStoreRepository
): LocalUserRepository {
    override suspend fun saveUser(user: User): Boolean{
        return try {
            userDao.setUser(user.toUserEntity())
            dataStoreRepository.setCurrentUserId(userDao.getUser().id)
            true
        }catch (e: Exception){
            Napier.e("saving user error: $e")
            false
        }
    }

    override suspend fun updateUser(user: User): Boolean{
        return try {
            val rowsUpdated = userDao.updateUser(user.toUserEntity())
            rowsUpdated > 0
        }catch (e: Exception){
            Napier.e("updating user error $e")
            false
        }
    }

    override suspend fun getUser(): User?{
        return try {
            userDao.getUser().toUser()
        }catch (e: Exception){
            Napier.e("getting user by id error:x $e")
            null
        }
    }

    override suspend fun deleteUser(): Boolean{
        return try {
            userDao.deleteUser()
            true
        }catch (e: Exception){
            Napier.e("getting user by id error:x $e")
            false
        }
    }
}

private fun User.toUserEntity(): UserEntity{
    return UserEntity(
        id = id,
        name = name,
        email = email,
        phone = phone
    )
}

private fun UserEntity.toUser(): User{
    return User(
        id = id,
        name = name,
        email = email,
        phone = phone,
        password = ""
    )
}