package org.example.project.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.example.project.core.database.converters.Converters
import org.example.project.core.database.dao.CourseDao
import org.example.project.core.database.dao.UserDao
import org.example.project.core.database.model.CourseEntity
import org.example.project.core.database.model.UserEntity

@Database(
    entities = [
        CourseEntity::class,
        UserEntity::class
    ],
    version = 1
)

@TypeConverters(Converters::class)
abstract class StepikDatabase: RoomDatabase() {
    abstract fun getCourseDao(): CourseDao
    abstract fun getUserDao(): UserDao
}