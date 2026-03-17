package org.example.project.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import org.example.project.core.database.model.UserEntity

@Dao
interface UserDao {
    @Upsert
    suspend fun setUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity): Int

    @Query("SELECT * FROM user_table limit 1")
    suspend fun getUser(): UserEntity

    @Query("DELETE FROM user_table")
    suspend fun deleteUser()
}