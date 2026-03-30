package org.gulnazidr.stepik.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.gulnazidr.stepik.core.room.converters.Converters
import org.gulnazidr.stepik.core.room.dao.CourseDao
import org.gulnazidr.stepik.core.room.model.CourseEntity

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
