package org.example.project.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.example.project.core.database.converters.Converters
import org.example.project.core.database.dao.CourseDao
import org.example.project.core.database.model.CourseEntity

@Database(
    entities = [
        CourseEntity::class
    ],
    version = 1,
    exportSchema = true,
)

@TypeConverters(Converters::class)
abstract class StepikDatabase: RoomDatabase() {
    abstract fun getCourseDao(): CourseDao
}
