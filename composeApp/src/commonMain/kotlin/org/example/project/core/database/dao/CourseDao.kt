package org.example.project.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import org.example.project.core.database.model.CourseEntity
import org.example.project.core.database.model.UserEntity

@Dao
interface CourseDao {
    @Upsert
    suspend fun setCourseList(courseList: List<CourseEntity>)

    @Update
    suspend fun updateCourse(courseDetailed: CourseEntity)

    @Query("SELECT * FROM course_table")
    suspend fun getCourseList(): List<CourseEntity>

    @Query("SELECT * FROM course_table where id = :id")
    suspend fun getCourseById(id: Int): CourseEntity?

    @Query("DELETE FROM course_table")
    suspend fun deleteCourses()
}